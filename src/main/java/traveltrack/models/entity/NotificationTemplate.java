package traveltrack.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import traveltrack.models.enums.NotificationChannel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventCode;
    private String status;
    @Column(columnDefinition="TEXT")
    private String messageTemplate;
    @Column(columnDefinition="TEXT")
    private String subject;
    @Enumerated(EnumType.STRING)
    private NotificationChannel notificationChanel;
}
