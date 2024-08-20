package kafkademo.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafkademo.notificationservice.TelegramBot;
import kafkademo.notificationservice.model.NotificationData;
import kafkademo.notificationservice.model.VerificationData;
import kafkademo.notificationservice.service.NotificationService;
import kafkademo.notificationservice.service.VerificationService;
import kafkademo.notificationservice.service.impl.strategy.NotificationHandlerFactory;
import kafkademo.notificationservice.util.TextConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final VerificationService emailService;
    private final NotificationHandlerFactory notificationHandlerFactory;
    private final TelegramBot telegramBot;
    private final ObjectMapper objectMapper;
    @Value("${base.url}")
    private String baseUrl;

    @KafkaListener(topics = "email-validation-response-topic", groupId = "task-manager-systems")
    public void sendVerificationEmail(ConsumerRecord<String, String> record)
            throws JsonProcessingException {
        String token = record.key();
        VerificationData verificationData =
                objectMapper.readValue(record.value(), VerificationData.class);
        if (verificationData.isPresent()) {
            String link = TextConstant.VERIFICATION_LINK.formatted(baseUrl, token);
            telegramBot.sendMessage(verificationData.getChatId(), TextConstant.USER_IS_PRESENT);
            emailService.sendVerification(verificationData.getEmail(), link);
        } else {
            telegramBot.sendMessage(verificationData.getChatId(),
                    TextConstant.USER_NOT_PRESENT.formatted(verificationData.getEmail()));
        }
    }

    @KafkaListener(topics = "notification-topic", groupId = "task-manager-systems")
    public void sendNotification(ConsumerRecord<String, String> record)
            throws JsonProcessingException {
        NotificationData notificationData =
                objectMapper.readValue(record.value(), NotificationData.class);
        String notificationType = notificationData.getChatId() == null ? "EMAIL" : "TELEGRAM";
        NotificationService notificationService =
                notificationHandlerFactory.getNotificationService(notificationType);
        notificationService.sendNotification(notificationData);
        //TODO: check logic
    }
}
