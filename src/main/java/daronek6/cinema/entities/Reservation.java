package daronek6.cinema.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Reservation {

    private @Id @GeneratedValue Long id;
    @NonNull
    @ManyToOne
    private Screening screening;
    @NonNull
    @ManyToOne
    private Client client;
    @NonNull
    @ManyToMany
    private Seat[] seats;
    @NonNull
    private double total;
    @NonNull
    private LocalDateTime expiration;

}
