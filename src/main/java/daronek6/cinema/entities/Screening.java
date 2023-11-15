package daronek6.cinema.entities;

import daronek6.cinema.entities.Movie;
import daronek6.cinema.entities.Room;
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
public class Screening {
    private @Id @GeneratedValue Long id;
    @NonNull
    @ManyToOne
    private Room room;
    @NonNull
    @ManyToOne
    private Movie movie;
    @NonNull
    private LocalDateTime start;
}
