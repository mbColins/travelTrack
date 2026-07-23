package traveltrack.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Data
public class UnAuthorizedUserException extends RuntimeException {

    private  String message;

    public UnAuthorizedUserException(String message) {
        super(message);
    }



}