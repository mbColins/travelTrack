package traveltrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import traveltrack.models.entity.NotificationTemplate;

import java.util.Optional;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate,Long> {
    Optional<NotificationTemplate> findByEventCode(String event);
}
