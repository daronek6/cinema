package daronek6.cinema.services;

import daronek6.cinema.dto.ScreeningInfoDto;
import daronek6.cinema.entities.*;
import daronek6.cinema.dto.SeatDto;
import daronek6.cinema.exceptions.EntityNotFoundException;
import daronek6.cinema.repositories.ScreeningRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepo repo;
    private final MovieService movieService;
    private final ReservationService reservationService;

    public List<Screening> getAll() {
        return repo.findAll();
    }

    public List<Screening> getAllByMovieTitle(String title) {
        Movie movie = movieService.getByTitle(title);
        return repo.findAllByMovie(movie);
    }

    public List<Screening> getAllByMovieTitleAndTimeFrame(String title, LocalDateTime timeFrameStart, LocalDateTime timeFrameEnd) {
        List<Screening> screeningsByTitle = getAllByMovieTitle(title);
        List<Screening> screeningsByTimeFrame = new java.util.ArrayList<>(screeningsByTitle.stream().filter((screening -> timeFrameStart.isBefore(screening.getStart()) && timeFrameEnd.isAfter(screening.getStart()))).toList());
        screeningsByTimeFrame.sort(Comparator.comparing(screening -> ((Screening)screening).getMovie().getTitle()).thenComparing(screening -> ((Screening)screening).getStart()));
        return screeningsByTimeFrame;
    }

    public List<Screening> getAllMoviesByTimeFrame(LocalDateTime timeFrameStart, LocalDateTime timeFrameEnd) {
        List<Screening> screeningsByTimeFrame = repo.findAllByStartBetween(timeFrameStart, timeFrameEnd);
        screeningsByTimeFrame.sort(Comparator.comparing(screening -> ((Screening)screening).getMovie().getTitle()).thenComparing(screening -> ((Screening)screening).getStart()));
        return  screeningsByTimeFrame;
    }

    public ScreeningInfoDto getScreeningInfo(Long id) {
        Screening screening = get(id);
        List<Reservation> reservations = reservationService.getReservationsForScreening(screening);
        List<SeatDto> reservedSeatDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            for (Seat seat : reservation.getSeats()) {
                reservedSeatDtos.add(new SeatDto(seat.getSeatRow(), seat.getSeatCol()));
            }
        }
        return new ScreeningInfoDto(screening, reservedSeatDtos);
    }
    public Screening get(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Screening add(Screening screening) {
        return repo.save(screening);
    }
}
