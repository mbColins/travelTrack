package traveltrack.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import traveltrack.models.enums.NotificationChannel;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String email;
    private String phoneNumber;
    private Boolean used;
    private Long expiresIn;
    private String destination;
    private String otp;
    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private OffsetDateTime expiresAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
