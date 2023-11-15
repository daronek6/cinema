package daronek6.cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Room {
    private @Id @GeneratedValue Long id;
    @NonNull
    private String name;
    @NonNull
    private int numOfRows;
    @NonNull
    private int rowLength;

}
