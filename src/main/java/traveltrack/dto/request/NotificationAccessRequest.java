package traveltrack.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationAccessRequest {
    private String smtpHost;
    private Long port;
    private String institutionEmail;
    private String institutionEmailPassword;
    private String issuer;
}
