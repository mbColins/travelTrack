package traveltrack.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import traveltrack.models.enums.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "tenant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String subDomain;
    @Column(nullable = false)
    private String personalisedDomain;
    private String subscriptionPlan;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String currency;
    private byte logo;
    private String languageChoice;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
