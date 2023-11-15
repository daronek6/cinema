package daronek6.cinema.services;

import daronek6.cinema.entities.Client;
import daronek6.cinema.exceptions.EntityNotFoundException;
import daronek6.cinema.exceptions.InvalidReservationDataException;
import daronek6.cinema.repositories.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepo repo;

    public Client get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Client add(Client client) {
        //can add checking if client exists. In current form {name, surname} it is not needed
        return repo.save(client);
    }

    public Client update(Client client, Long id) {
        return repo.findById(id)
                .map((oldClient) -> {
                    oldClient.setName(client.getName());
                    oldClient.setSurname(client.getSurname());
                    return repo.save(oldClient);
                }).orElseGet(() -> {
                    client.setId(id);
                    return repo.save(client);
                });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Boolean isValid(Client client) {
        String namePatternStr = "[A-Z|ĘÓĄŚŁŻŹĆŃ][a-z|ęóąśłżźćń]{2,}$"; //First capital letter, at least 3 characters long and polish letters
        String surnamePatternStr = "[A-Z|ĘÓĄŚŁŻŹĆŃ][a-z|ęóąśłżźćń]{2,}(-[A-Z|ĘÓĄŚŁŻŹĆŃ][a-z|ęóąśłżźćń]{2,})?$"; //First capital letter, at least 3 characters long and polish letters with possible two part name

        Pattern namePattern = Pattern.compile(namePatternStr);
        Pattern surnamePattern = Pattern.compile(surnamePatternStr);

        if (namePattern.matcher(client.getName()).matches() & surnamePattern.matcher(client.getSurname()).matches())
            return true;
        else
            throw new InvalidReservationDataException("Not valid name or surname");

    }

}
