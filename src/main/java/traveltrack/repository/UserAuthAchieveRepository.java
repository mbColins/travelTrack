package traveltrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import traveltrack.models.entity.User;
import traveltrack.models.entity.UserAuthAchieve;

import java.util.List;

public interface UserAuthAchieveRepository extends JpaRepository<UserAuthAchieve,Long> {

    List<UserAuthAchieve> findByUser(User user);

    long countByUser(User user);

    void deleteAllByUser(User user);
}
