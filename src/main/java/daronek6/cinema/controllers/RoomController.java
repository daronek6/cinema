package daronek6.cinema.controllers;

import daronek6.cinema.entities.Room;
import daronek6.cinema.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;
    @PostMapping("/room")
    Room add(@RequestBody Room room) {
        return service.add(room);
    }
}
