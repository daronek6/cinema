package daronek6.cinema.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SeatDto {
    private final Long row;
    private final Long col;
}
