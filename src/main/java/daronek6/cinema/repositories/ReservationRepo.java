package daronek6.cinema.repositories;

import daronek6.cinema.entities.Reservation;
import daronek6.cinema.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByScreening(Screening screening);
}
