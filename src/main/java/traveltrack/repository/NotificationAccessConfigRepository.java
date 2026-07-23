package traveltrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import traveltrack.models.entity.NotificationAccessConfig;
import traveltrack.models.entity.User;

import java.util.Optional;

public interface NotificationAccessConfigRepository extends JpaRepository<NotificationAccessConfig,Long> {
    Optional<NotificationAccessConfig> findByUserUsername(String user);
}
