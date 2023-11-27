package daronek6.cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Seat {
    private @Id @GeneratedValue Long id;
    @NonNull
    private Long seatRow;
    @NonNull
    private Long seatCol;
}
