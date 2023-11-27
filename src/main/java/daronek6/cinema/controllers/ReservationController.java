package daronek6.cinema.controllers;

import daronek6.cinema.dto.ReservationInfoDto;
import daronek6.cinema.entities.Reservation;
import daronek6.cinema.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping("/reservation/screening/{screeningId}")
    Reservation add(@PathVariable Long screeningId, @RequestBody ReservationInfoDto reservationInfoDto) {
        return service.add(screeningId, reservationInfoDto);
    }


}
