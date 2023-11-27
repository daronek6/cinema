package daronek6.cinema.util;

import daronek6.cinema.entities.*;
import daronek6.cinema.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner init(MovieRepo movieRepo, RoomRepo roomRepo, //
                           ScreeningRepo screeningRepo, SeatRepo seatRepo, TicketRepo ticketRepo) {
        Room room1 = new Room("Small Room", 5, 5);
        Room room2 = new Room("Medium room", 6, 8);
        Room room3 = new Room("Big room", 8, 10);
        Movie movie1 = new Movie("Movie The Movie", 110.0, 110.0/60.0);
        Movie movie2 = new Movie("Spooky Movie", 140.0, 140.0/60.0);
        Movie movie3 = new Movie("Funny Movie", 90.0, 90.0/60.0);
        Ticket adultTicket = new Ticket(Ticket.TicketType.ADULT, 25.0, LocalDateTime.now());
        Ticket studentTicket = new Ticket(Ticket.TicketType.STUDENT, 18.0, LocalDateTime.now());
        Ticket childTicket = new Ticket(Ticket.TicketType.CHILD, 12.5, LocalDateTime.now());
        Screening room1Screening1 = new Screening(room1, movie1, LocalDateTime.now().plusHours(2));
        Screening room1Screening2 = new Screening(room1, movie1, LocalDateTime.now().plusHours(2).plusMinutes((long) movie1.getScreeningTimeM() + 15));
        Screening room2Screening1 = new Screening(room2, movie2, LocalDateTime.now().plusMinutes(15));
        Screening room2Screening2 = new Screening(room2, movie3, LocalDateTime.now().plusMinutes(15).plusMinutes((long) movie2.getScreeningTimeM() + 30));
        Screening room3Screening1 = new Screening(room3, movie1, LocalDateTime.now().plusMinutes(30));
        Screening room3Screening2 = new Screening(room3, movie3, LocalDateTime.now().plusMinutes(30).plusMinutes((long) movie1.getScreeningTimeM() + 30));
        Screening room3Screening3 = new Screening(room3, movie2, LocalDateTime.now().plusMinutes(30).plusMinutes((long) movie1.getScreeningTimeM() + 30).plusMinutes((long) movie3.getScreeningTimeM() + 30));
        Seat[] seats = new Seat[room3.getNumOfRows()*room3.getRowLength()];
        int seatIdx = 0;
        Seat tmpSeat;
        for (long i = 0; i < room3.getNumOfRows(); i++) {
            for (long j = 0; j < room3.getRowLength(); j++) {
                tmpSeat = new Seat(i, j);
                seats[seatIdx] = tmpSeat;
                seatIdx++;
            }

        }

        return args -> {
            log.info("Adding data " + roomRepo.save(room1));
            log.info("Adding data " + roomRepo.save(room2));
            log.info("Adding data " + roomRepo.save(room3));
            log.info("Adding data " + movieRepo.save(movie1));
            log.info("Adding data " + movieRepo.save(movie2));
            log.info("Adding data " + movieRepo.save(movie3));
            log.info("Adding data " + ticketRepo.save(adultTicket));
            log.info("Adding data " + ticketRepo.save(studentTicket));
            log.info("Adding data " + ticketRepo.save(childTicket));
            log.info("Adding data " + screeningRepo.save(room1Screening1));
            log.info("Adding data " + screeningRepo.save(room1Screening2));
            log.info("Adding data " + screeningRepo.save(room2Screening1));
            log.info("Adding data " + screeningRepo.save(room2Screening2));
            log.info("Adding data " + screeningRepo.save(room3Screening1));
            log.info("Adding data " + screeningRepo.save(room3Screening2));
            log.info("Adding data " + screeningRepo.save(room3Screening3));
            for (Seat seat : seats) {
                log.info("Adding data " + seatRepo.save(seat));
            }
        };
    }
}
