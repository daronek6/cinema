package daronek6.cinema.dto;

import daronek6.cinema.entities.Screening;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ScreeningInfoDto {
    private final Screening screening;
    private final List<SeatDto> reservedSeats;
}
