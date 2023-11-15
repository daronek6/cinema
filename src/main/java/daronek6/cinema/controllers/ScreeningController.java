package daronek6.cinema.controllers;

import daronek6.cinema.dto.ScreeningInfoDto;
import daronek6.cinema.entities.Screening;
import daronek6.cinema.services.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService service;

    @GetMapping("/screenings")
    List<Screening> getAll() {
        return service.getAll();
    }
    @GetMapping(value = "/screenings" , params = "title")
    List<Screening> getAllByMovieTitle(@RequestParam String title) {
        return service.getAllByMovieTitle(title);
    }
    @GetMapping(value = "/screenings" , params = {"start", "end"})
    List<Screening> getAllByMovieTitleAndTimeFrame(@RequestParam String start, @RequestParam String end) {
        return service.getAllMoviesByTimeFrame(LocalDateTime.parse(start), LocalDateTime.parse(end));
    }
    @GetMapping(value = "/screenings" , params = {"title", "start", "end"})
    List<Screening> getAllByMovieTitleAndTimeFrame(@RequestParam  String title, @RequestParam String start, @RequestParam String end) {
        return service.getAllByMovieTitleAndTimeFrame(title, LocalDateTime.parse(start), LocalDateTime.parse(end));
    }
    @GetMapping("/screening/{id}")
    ScreeningInfoDto getScreeningInfo(@PathVariable Long id) {
        return service.getScreeningInfo(id);
    }
}
