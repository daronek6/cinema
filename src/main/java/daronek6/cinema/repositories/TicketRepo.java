package daronek6.cinema.repositories;

import daronek6.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByPriceEndTime(LocalDateTime priceEndTime);
}
