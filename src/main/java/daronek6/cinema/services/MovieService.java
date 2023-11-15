package daronek6.cinema.services;

import daronek6.cinema.entities.Movie;
import daronek6.cinema.exceptions.EntityNotFoundException;
import daronek6.cinema.repositories.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepo repo;

    public List<Movie> getAll() {
        return repo.findAll();
    }

    public Movie get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException((id)));
    }

    public Movie add(Movie movie) {
        return repo.save(movie);
    }

    public Movie update(Movie movie, Long id) {
        return repo.findById(id)
                .map((oldMovie) -> {
                    oldMovie.setTitle(movie.getTitle());
                    oldMovie.setScreeningTimeH(movie.getScreeningTimeH());
                    oldMovie.setScreeningTimeM(movie.getScreeningTimeM());
                    return repo.save(oldMovie);
                }).orElseGet(() -> {
                    movie.setId(id);
                    return repo.save(movie);
                });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Movie getByTitle(String title) {
        return repo.findByTitle(title);
    }
}
