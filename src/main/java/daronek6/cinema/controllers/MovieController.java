package daronek6.cinema.controllers;

import daronek6.cinema.entities.Movie;
import daronek6.cinema.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;
    @GetMapping("/movies")
    List<Movie> getAll() {
        return service.getAll();
    }

    @GetMapping("/movie/{id}")
    Movie get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/movie")
    Movie add(@RequestBody Movie movie) {
        return service.add(movie);
    }

    @PutMapping("/movie/{id}")
    Movie update(@RequestBody Movie movie, @PathVariable Long id) {
        return service.update(movie, id);
    }

    @DeleteMapping("/movie/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
