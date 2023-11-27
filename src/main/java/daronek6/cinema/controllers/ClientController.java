package daronek6.cinema.controllers;

import daronek6.cinema.entities.Client;
import daronek6.cinema.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping("/client/{id}")
    Client get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/client")
    Client add(@RequestBody Client client) {
        return service.add(client);
    }

    @PutMapping("/client/{id}")
    Client update(@RequestBody Client client, @PathVariable Long id) {
        return service.update(client, id);
    }

    @DeleteMapping("client/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
