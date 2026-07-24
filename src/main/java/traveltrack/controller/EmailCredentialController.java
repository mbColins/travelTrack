package traveltrack.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import traveltrack.dto.request.NotificationAccessRequest;
import traveltrack.helpers.ResponseHandler;
import traveltrack.models.entity.User;
import traveltrack.service.EmailCredentialService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class EmailCredentialController {
    private final EmailCredentialService emailCredentialService;

    @PostMapping
    public ResponseEntity<Object> createCredentials(@RequestBody NotificationAccessRequest dto, @AuthenticationPrincipal User user,
                                                    HttpServletRequest request) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String response = emailCredentialService.createNewCredentials(dto,user,request);
        return ResponseHandler.generateResponse(HttpStatus.OK,false,"success",response);
    }
}
