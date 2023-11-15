package daronek6.cinema.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReservationInfoDto {
    private final String name;
    private final String surname;
    private final SeatDto[] seats;
    private final Long adultTickets;
    private final Long studentTickets;
    private final Long childrenTickets;
}
