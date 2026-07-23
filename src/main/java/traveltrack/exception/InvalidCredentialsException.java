package traveltrack.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Data
public class InvalidCredentialsException extends RuntimeException {

    private String message;

    public InvalidCredentialsException(String message) {
        super(message);
    }

}
