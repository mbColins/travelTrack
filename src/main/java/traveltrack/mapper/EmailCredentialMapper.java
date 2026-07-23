package traveltrack.mapper;

import traveltrack.dto.request.NotificationAccessRequest;
import traveltrack.models.entity.NotificationAccessConfig;

public class EmailCredentialMapper {
    public static NotificationAccessConfig toModel(NotificationAccessRequest notificationAccessRequest){
        return NotificationAccessConfig.builder()
                .smtpHost(notificationAccessRequest.getSmtpHost())
                .port(notificationAccessRequest.getPort())
                .institutionEmail(notificationAccessRequest.getInstitutionEmail())
                .issuer(notificationAccessRequest.getIssuer())
                .build();
    }
}
