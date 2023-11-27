package daronek6.cinema.services;

import daronek6.cinema.entities.Ticket;
import daronek6.cinema.repositories.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepo repo;

    public Ticket add(Ticket ticket) {
        return repo.save(ticket);
    }
    public double getTotal(Ticket[] tickets) {
        double total = 0.0;
        for (Ticket ticket : tickets) {
            total += ticket.getPrice();
        }
        return total;
    }

    public double getTotal(Long adultTickets, Long studentTickets, Long childrenTickets) {
        List<Ticket> currentTickets = repo.findAllByPriceEndTime(null);
        Ticket adultTicket = currentTickets.stream().dropWhile(ticket -> ticket.getType() == Ticket.TicketType.ADULT).toList().get(0);
        Ticket studentTicket = currentTickets.stream().dropWhile(ticket -> ticket.getType() == Ticket.TicketType.STUDENT).toList().get(0);
        Ticket childrenTicket = currentTickets.stream().dropWhile(ticket -> ticket.getType() == Ticket.TicketType.CHILD).toList().get(0);

        return adultTicket.getPrice() * adultTickets + studentTicket.getPrice() * studentTickets + childrenTicket.getPrice() * childrenTickets;
    }
}
