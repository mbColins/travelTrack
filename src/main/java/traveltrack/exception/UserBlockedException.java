package traveltrack.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED)
@Data
public class UserBlockedException extends RuntimeException {

    private String message;

    public UserBlockedException(String message) {
        super(message);
    }

}
