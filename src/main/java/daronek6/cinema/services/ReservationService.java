package daronek6.cinema.services;

import daronek6.cinema.dto.ReservationInfoDto;
import daronek6.cinema.entities.*;
import daronek6.cinema.dto.SeatDto;
import daronek6.cinema.exceptions.EntityNotFoundException;
import daronek6.cinema.exceptions.InvalidReservationDataException;
import daronek6.cinema.repositories.*;
import daronek6.cinema.util.LoadData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private static final Logger log = LoggerFactory.getLogger(LoadData.class);
    private final Long MAX_TIME_BEFORE_SCREENING = 15L;

    private final ReservationRepo repo;
    private final ScreeningRepo screeningRepo;

    private final ClientService clientService;
    private final RoomService roomService;
    private final SeatService seatService;
    private final TicketService ticketService;

    public Reservation add(Reservation reservation, Long adultTickets, Long studentTickets, Long childrenTickets) {
        if (isValid(reservation)) {
            Client savedClient = clientService.add(reservation.getClient());
            reservation.setClient(savedClient);
            reservation.setTotal(ticketService.getTotal(adultTickets, studentTickets, childrenTickets));
            reservation.setExpiration(reservation.getScreening().getStart().plusMinutes(((long) reservation.getScreening().getMovie().getScreeningTimeM())));
            log.info(reservation.toString());
            return repo.save(reservation);
        }
        else return null;
    }

    public Reservation add(Reservation reservation, Ticket[] tickets) {
        if (isValid(reservation)) {
            reservation.setTotal(ticketService.getTotal(tickets));
            reservation.setExpiration(reservation.getScreening().getStart().plusMinutes(((long) reservation.getScreening().getMovie().getScreeningTimeM())));
            return repo.save(reservation);
        }
        else
            return null;
    }

    public Reservation add(Long screeningId, String name, String surname, SeatDto[] seatDtos, Long adultTickets, Long studentTickets, Long childrenTickets) {
        Screening screening = screeningRepo.findById(screeningId).orElseThrow(() -> new EntityNotFoundException(screeningId));
        Client client = new Client(name, surname);
        Reservation reservation = new Reservation();
        reservation.setScreening(screening);
        reservation.setClient(client);
        Seat[] seatEntities = seatService.getSeatEntities(seatDtos);
        reservation.setSeats(seatEntities);

        return add(reservation, adultTickets, studentTickets, childrenTickets);
    }

    public Reservation add(Long screeningId, ReservationInfoDto reservationInfoDto) {
        log.info(reservationInfoDto.toString());
        return add(screeningId, reservationInfoDto.getName(), reservationInfoDto.getSurname(),    //
                reservationInfoDto.getSeats(), reservationInfoDto.getAdultTickets(),              //
                reservationInfoDto.getStudentTickets(), reservationInfoDto.getChildrenTickets());
    }

    public Reservation get(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Boolean isValid(Reservation reservation) {
        return (validReservationTime(reservation) & //Reserved before time limit
                clientService.isValid(reservation.getClient()) & //Client credentials are valid
                roomService.seatsExist(reservation.getScreening().getRoom(), reservation.getSeats()) & //Reserved seats exists in this room
                seatService.seatsNotYetReserved(reservation.getSeats(), getReservedSeats(reservation.getScreening())) & //Reserved seats are not yet reserved
                seatService.isValid(reservation.getSeats())); //There is no singe gap between reserved seats in any row
    }

    public Boolean validReservationTime(Reservation reservation) {
        LocalDateTime now = LocalDateTime.now();
        now = now.plusMinutes(MAX_TIME_BEFORE_SCREENING);
        if (now.isBefore(reservation.getScreening().getStart()))
            return true;
        else
            throw new InvalidReservationDataException("Reservation must be done " + MAX_TIME_BEFORE_SCREENING + " min before screening!");
    }

    public List<Reservation> getReservationsForScreening(Screening screening) {
        return repo.getAllByScreening(screening);
    }

    public List<Seat> getReservedSeats(Screening screening) {
        List<Reservation> reservations = getReservationsForScreening(screening);
        List<Seat> reservedSeats = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservedSeats.addAll(Arrays.stream(reservation.getSeats()).toList());
        }

        return reservedSeats;
    }

}
