package daronek6.cinema.repositories;

import daronek6.cinema.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat, Long> {
    Seat findBySeatRowAndSeatCol(Long seatRow, Long seatCol);
}
