package traveltrack.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class ErrorDetails {
    private final Date timestamp;
    private final String message;
    private final String error;
    private final String details;

    private final Object object;

    public ErrorDetails(Date timestamp, String message, String details, Object object, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.error = error;
        this.details = details;
        this.object = object;
    }

    public ErrorDetails(Date timestamp, String message, Object object, String error) {
        this(timestamp, message, null, object, error);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getError(){return error;}
    public Object getObject() {
        return object;
    }
}
