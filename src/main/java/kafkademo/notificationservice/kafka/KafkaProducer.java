package kafkademo.notificationservice.kafka;

import com.example.dto.VerificationData;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEmailToValidate(String email, Long chatId, String token) {
        try {
            VerificationData verificationData = new VerificationData();
            verificationData.setEmail(email);
            verificationData.setChatId(chatId.toString());
            verificationData.setCreatedAt(LocalDateTime.now());
            verificationData.setPresent(false);
            String topic = "email-validation-topic";
            ProducerRecord<String, Object> record =
                    new ProducerRecord<>(topic, token, verificationData);
            kafkaTemplate.send(record);
            log.info("Sent record: {} to the topic {}", record, topic);
        } catch (Exception e) {
            log.error("Error sending response: {}", e.getMessage());
        }
    }
}
