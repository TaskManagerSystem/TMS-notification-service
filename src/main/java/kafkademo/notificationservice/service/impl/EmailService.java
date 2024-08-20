package kafkademo.notificationservice.service.impl;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import kafkademo.notificationservice.model.MessageData;
import kafkademo.notificationservice.model.UserData;
import kafkademo.notificationservice.service.NotificationService;
import kafkademo.notificationservice.service.VerificationService;
import kafkademo.notificationservice.util.TextConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service("EMAIL")
public class EmailService implements NotificationService, VerificationService {
    @Value("${service.email}")
    private String username;
    @Value("${service.password}")
    private String password;

    @Override
    public void sendNotification(UserData userData, MessageData messageData) {
        Session session = getSession();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(userData.getEmail()));
            message.setSubject(messageData.getSubject());
            String htmlMessage = formMessageForEmail(messageData.getText());
            message.setContent(htmlMessage, "text/html; charset=utf-8");
            Transport.send(message);
            log.info("Sent notification message to the email: {} successfully",
                    userData.getEmail());
        } catch (MessagingException e) {
            log.error("Can't send notification message to the email: {}",
                    userData.getEmail());
        }
    }

    @Override
    public void sendVerification(String recipientEmail, String link) {
        Session session = getSession();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Link Telegram to your TaskManagerSystems account");
            String htmlMessage = TextConstant.EMAIL_TEXT.formatted(link);
            message.setContent(htmlMessage, "text/html; charset=utf-8");
            Transport.send(message);
            log.info("Sent verification message to the email: {} successfully",
                    recipientEmail);
        } catch (MessagingException e) {
            log.error("Can't send verification message to the email: {}",
                    recipientEmail);
        }
    }

    private Session getSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private String formMessageForEmail(String message) {
        //TODO: logic for html message forming
        return null;
    }
}
