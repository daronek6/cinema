package daronek6.cinema.services;

import daronek6.cinema.entities.Room;
import daronek6.cinema.entities.Seat;
import daronek6.cinema.exceptions.EntityNotFoundException;
import daronek6.cinema.exceptions.InvalidReservationDataException;
import daronek6.cinema.repositories.RoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepo repo;

    public Room get(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }
    public Room add(Room room) {
        return repo.save(room);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Boolean seatsExist(Room room, Seat[] seats) {
        boolean test = true;
        for(Seat seat : seats) {
            test &= seatExists(room, seat);
        }
        if(test)
            return test;
        else
            throw new InvalidReservationDataException("One of the seats does not exist in this room!");
    }
    public Boolean seatExists(Room room, Seat seat) {
        return (seat.getSeatRow() >= 0 && seat.getSeatRow() <= room.getNumOfRows() - 1) && (seat.getSeatCol() >= 0 && seat.getSeatCol() <= room.getRowLength() - 1);
    }



}
