package daronek6.cinema.repositories;

import daronek6.cinema.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);
}
