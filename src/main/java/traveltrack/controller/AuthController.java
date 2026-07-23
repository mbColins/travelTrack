package traveltrack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import traveltrack.dto.request.LoginRequestDTO;
import traveltrack.dto.request.UserRequestDTO;
import traveltrack.dto.response.AuthResponseDTO;
import traveltrack.exception.AlreadyExistException;
import traveltrack.helpers.ResponseHandler;
import traveltrack.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO dto) {
        AuthResponseDTO response = authService.login(dto);
        return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(Authentication authentication) {
        authService.logout(authentication);
        return ResponseHandler.generateResponse(HttpStatus.OK, false, "logged out", null);
    }
}
