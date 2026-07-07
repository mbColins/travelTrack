package traveltrack.security.jwt;

import lombok.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiry}")
    private long jwtExpirationMs;
    @Value("${jwt.refresh-token-expiry}")
    private long refreshTokenExpiry;
}
