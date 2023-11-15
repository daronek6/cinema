package daronek6.cinema.errors;

import daronek6.cinema.exceptions.InvalidReservationDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidReservationDataAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidReservationDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidReservationDataHandler(InvalidReservationDataException e) {
        return e.getMessage();
    }
}
