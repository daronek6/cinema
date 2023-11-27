package daronek6.cinema.exceptions;

public class InvalidReservationDataException extends RuntimeException {
    public InvalidReservationDataException(String msg) {
        super("Invalid reservation data: " + msg);
    }
}
