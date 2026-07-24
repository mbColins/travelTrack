package traveltrack.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import traveltrack.dto.request.NotificationAccessRequest;
import traveltrack.exception.ResourceNotFoundException;
import traveltrack.helpers.Tools;
import traveltrack.helpers.Utils;
import traveltrack.mapper.EmailCredentialMapper;
import traveltrack.models.entity.NotificationAccessConfig;
import traveltrack.models.entity.User;
import traveltrack.repository.NotificationAccessConfigRepository;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class EmailCredentialService {
    private final NotificationAccessConfigRepository notificationAccessConfigRepository;
    private final Utils utils;

    public String createNewCredentials(NotificationAccessRequest notificationAccessRequest, User user, HttpServletRequest request) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        NotificationAccessConfig notificationAccessConfig = EmailCredentialMapper.toModel(notificationAccessRequest);
        notificationAccessConfig.setInstitutionEmailPassword(Tools.encrypt(notificationAccessRequest.getInstitutionEmailPassword()));
        notificationAccessConfig.setUser(user);
        notificationAccessConfig.setCreatedBy(user.getUsername());
        notificationAccessConfigRepository.save(notificationAccessConfig);
        return utils.getResponseMessage("new.credentials.added.successfully", null, request.getLocale());
    }

    public NotificationAccessConfig getByUser(String user) {
        return notificationAccessConfigRepository.findByUserUsername(user).orElseThrow(
                () -> new ResourceNotFoundException("user.not.found"));
    }

    public NotificationAccessConfig geCredentials() {
        return notificationAccessConfigRepository.findAll().stream().findFirst().orElseThrow(
                () -> new ResourceNotFoundException("no.credential.found"));
    }

}
