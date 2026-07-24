package traveltrack.rabbitMq.listeners;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import traveltrack.models.entity.NotificationEvent;
import traveltrack.models.entity.NotificationTemplate;
import traveltrack.repository.NotificationAccessConfigRepository;
import traveltrack.repository.NotificationTemplateRepository;
import traveltrack.service.EmailService;

import javax.security.auth.Subject;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailListener {
    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationAccessConfigRepository notificationAccessConfigRepository;
    private final EmailService emailService;

    private final Logger log = LogManager.getLogger(EmailListener.class);

    public void index(NotificationEvent event){
        log.info("entering the email listener with event {}",event);
        Optional<NotificationTemplate> template = notificationTemplateRepository.findByEventCode(String.valueOf(event.getEvent()));
        String body;
        String subject;
        if(template.isPresent()){
            body = event.getBody(template.get().getMessageTemplate());
            subject = template.get().getSubject();
        }else {
            body = event.getPayloads().toString();
            subject = "Well come to travel track plus";
        }

        try {
            emailService.sendSimpleMessage(event.getDestination(),subject,body);
            log.info("===================" + event + "===================");
            log.info("===================" + "email sent"+ "===================");
        }catch (Exception e){
            log.error("error sending email", e);
            throw new RuntimeException(e);
        }
    }
}
