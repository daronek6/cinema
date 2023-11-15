package daronek6.cinema.controllers;

import daronek6.cinema.entities.Ticket;
import daronek6.cinema.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    @PostMapping("/ticket")
    Ticket add(@RequestBody Ticket ticket) {
        return service.add(ticket);
    }
}
