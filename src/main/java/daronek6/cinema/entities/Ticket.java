package daronek6.cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Ticket {

    private @Id @GeneratedValue Long id;
    @NonNull
    private TicketType type;
    @NonNull
    private double price;
    @NonNull
    private LocalDateTime priceStartTime;
    private LocalDateTime priceEndTime;

    public enum TicketType {
        ADULT, STUDENT, CHILD
    }
}


