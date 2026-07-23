package traveltrack.dto.response;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NotificationAccessResponse {
    private Long id;
    private String smtpHost;
    private Long port;
    private String institutionEmail;
    private String institutionEmailPassword;
    private String issuer;
    private String createdBy;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
