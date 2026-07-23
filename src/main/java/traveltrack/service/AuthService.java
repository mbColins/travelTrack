package traveltrack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import traveltrack.dto.request.LoginRequestDTO;
import traveltrack.dto.request.UserRequestDTO;
import traveltrack.dto.response.AuthResponseDTO;
import traveltrack.exception.*;
import traveltrack.models.entity.User;
import traveltrack.models.entity.UserAuthAchieve;
import traveltrack.models.enums.Status;
import traveltrack.repository.UserAuthAchieveRepository;
import traveltrack.repository.UserRepository;
import traveltrack.security.jwt.JwtService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAuthAchieveRepository userAuthAchieveRepository;
    private final SystemConfigService systemConfigService;



    @Transactional
    public AuthResponseDTO login(LoginRequestDTO request) {
        long maxTrials = getMaxTrialsOrDefault();
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);

        if (user == null) {
            throw new InvalidCredentialsException("invalid.credentials");
        }

        if (Status.BLOCKED.equals(user.getStatus())) {
            throw new UserBlockedException("user.blocked");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            resetFailedAttempts(user);
            return buildTokens(user);

        } catch (AuthenticationException ex) {
            return handleFailedAttempt(user, maxTrials);
        }
    }
    private long getMaxTrialsOrDefault() {
       return systemConfigService.getSystemConfiguration().getMaxAuthAttempt();
    }


    private AuthResponseDTO handleFailedAttempt(User user, long maxTrials) {
        long currentAttempts = userAuthAchieveRepository.countByUser(user);
        userAuthAchieveRepository.save(UserAuthAchieve.builder().user(user).build());

        long totalAttempts = currentAttempts + 1;
        long remainingTrials = maxTrials - totalAttempts;   // decrements by 1 each failed call

        if (totalAttempts >= maxTrials) {
            user.setStatus(Status.BLOCKED);
            userRepository.save(user);
            throw new UserBlockedException("user.blocked");
        }

        return AuthResponseDTO.builder()
                .remainingTrials(remainingTrials)
                .build();

    }


    private void resetFailedAttempts(User user) {
        userAuthAchieveRepository.deleteAllByUser(user);
    }

    public void logout(Authentication authentication) {
        SecurityContextHolder.clearContext();
    }

    private AuthResponseDTO buildTokens(User user) {
        return AuthResponseDTO.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }
}
