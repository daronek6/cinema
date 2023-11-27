package daronek6.cinema.services;

import daronek6.cinema.dto.SeatDto;
import daronek6.cinema.entities.Seat;
import daronek6.cinema.exceptions.InvalidReservationDataException;
import daronek6.cinema.repositories.SeatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepo repo;

    private final RoomService roomService;

    public Boolean seatsNotYetReserved(Seat[] newReservation, List<Seat> reserved) {
        boolean test = true;
        for (Seat seat : newReservation) {
            test &= !reserved.contains(seat);
        }
        if(test)
            return test;
        else
            throw new InvalidReservationDataException("One of seats is already reserved!");
    }
    public Boolean isValid(Seat[] seats) {
        if(seats == null || seats.length <= 0)
            throw new InvalidReservationDataException("You must reserve at least one seat!");
        boolean test = true;
        List<Long> rows = Arrays.stream(seats).map(Seat::getSeatRow).collect(Collectors.toSet()).stream().toList();
        ArrayList<Seat>[] separateRows = new ArrayList[rows.size()];

        for (int i = 0; i < rows.size(); i++) {
            separateRows[i] = new ArrayList<>();
        }

        for(Seat seat : seats) { //Separating reserved seats to rows
            separateRows[rows.indexOf(seat.getSeatRow())].add(seat);
        }

        for(ArrayList<Seat> row : separateRows) { //Sorting reserved seats in rows for easier checking for a gap
            row.sort(Comparator.comparing(Seat::getSeatCol));
        }

        for(ArrayList<Seat> row : separateRows) { //Checking for a gap in each row
            test &= isValidInRow(row);
        }
        if(test)
            return test;
        else
            throw new InvalidReservationDataException("There must not be a single seat gap between reserved seats!");
    }

    private Boolean isValidInRow(List<Seat> row) { //Checking if reservation does not have single gap seat in row and there are no duplicated reservations
        boolean test = true;
        for (int i = 1; i < row.size() ; i++) {
            test &= ( Math.abs(row.get(i).getSeatCol() - row.get(i - 1).getSeatCol()) != 2 && //No single gap
                    Math.abs(row.get(i).getSeatCol() - row.get(i - 1).getSeatCol()) > 0); //Previous seat is different
        }
        return test;
    }

    public Seat[] getSeatEntities(SeatDto[] seatDtos) {
        return Arrays.stream(seatDtos).sequential().map((seat -> repo.findBySeatRowAndSeatCol(seat.getRow(),seat.getCol()))).toList().toArray(new Seat[seatDtos.length]);
    }
}
