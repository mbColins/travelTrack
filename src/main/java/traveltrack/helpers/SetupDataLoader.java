package traveltrack.helpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import traveltrack.models.enums.UserType;
import traveltrack.models.entity.User;
import traveltrack.repository.UserRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SetupDataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.admin.email:admin@traveltrack.local}")
    private String adminEmail;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByUsername("SystemAdmin").isPresent()) {
            log.info("Setup: user '{}' already exists, skipping seed.", "SystemAdmin");
            return;
        }
        User admin = User.builder()
                .uuid(UUID.randomUUID().toString())
                .username("SystemAdmin")
                .firstname("System")
                .lastname("Administrator")
                .email(adminEmail)
                .phoneNumber("653381110")
                .password(passwordEncoder.encode("ChangeMe123"))
                .country("Cameroon")
                .neighbourhood("Douala")
                .userType(UserType.SYSTEM_ADMIN)
                .createdBy("SYSTEM")
                .build();
        userRepository.save(admin);
        log.info("Setup: created default admin user '{}'.",admin.getUsername());
    }
}
