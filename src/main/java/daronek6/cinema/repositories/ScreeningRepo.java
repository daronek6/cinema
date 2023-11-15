package daronek6.cinema.repositories;

import daronek6.cinema.entities.Movie;
import daronek6.cinema.entities.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepo extends JpaRepository<Screening, Long> {
    List<Screening> findAllByMovie(Movie movie);
    List<Screening> findAllByStartBetween(LocalDateTime startFrame, LocalDateTime endFrame);
}
