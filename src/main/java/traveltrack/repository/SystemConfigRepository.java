package traveltrack.repository;

import traveltrack.models.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {

    List<SystemConfig> findByMaxAuthAttempt(Long maxAuthAttempt);

}