package kafkademo.notificationservice.service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import kafkademo.notificationservice.util.TextConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Value("${service.email}")
    private String username;
    @Value("${service.password}")
    private String password;

    public void sendVerificationCode(String recipientEmail, String token) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Link Telegram to your TaskManagerSystems account");
            String htmlMessage = TextConstant.EMAIL_TEXT.formatted(token);
            message.setContent(htmlMessage, "text/html; charset=utf-8");
            Transport.send(message);
            log.info("Sent message to the email: {} successfully", recipientEmail);
        } catch (MessagingException e) {
            log.error("Can't send message to the email: {}", recipientEmail);
        }
    }
}
