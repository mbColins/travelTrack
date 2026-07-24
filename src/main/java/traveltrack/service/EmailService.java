package traveltrack.service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import traveltrack.helpers.Tools;
import traveltrack.models.entity.NotificationAccessConfig;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailCredentialService emailCredentialService;
    private static final Logger log = LoggerFactory.getLogger(EmailCredentialService.class);

    public Session configure()  {
        NotificationAccessConfig config = emailCredentialService.geCredentials();
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true"); // Enable SSL
        props.put("mail.debug", "true");
        props.put("mail.smtp.host", config.getSmtpHost());
        props.put("mail.smtp.port", config.getPort());
        props.put("jakarta.mail.util.StreamProvider", "org.eclipse.angus.mail.util.MailStreamProvider");
        return Session.getDefaultInstance(props, new Authenticator() {
            @SneakyThrows
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getInstitutionEmail(), Tools.decrypt(config.getInstitutionEmailPassword()));
            }
        });
    }


    @Async
    public void sendSimpleMessage(String to, String subject, String text) throws UnsupportedEncodingException, MessagingException {
        NotificationAccessConfig config = emailCredentialService.geCredentials();
        Session session = configure();
        session.setDebug(false);
        session.setProtocolForAddress("rfc822", "smtp");
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(config.getInstitutionEmail(), config.getIssuer()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setContent(text,"text/html");
        Transport.send(message);
        log.info("Mail send successfully");
    }


}
