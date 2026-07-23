package traveltrack.exception;

public class SystemConfigNotFoundException extends RuntimeException {

    public SystemConfigNotFoundException(Long id) {
        super("SystemConfig not found with id: " + id);
    }

    public SystemConfigNotFoundException(String message) {
        super(message);
    }

}