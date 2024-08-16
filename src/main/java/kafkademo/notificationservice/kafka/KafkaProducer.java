package kafkademo.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmailToValidate(String email, Long chatId, String token) {
        String message = token + ":" + email + ":" + chatId;
        ProducerRecord<String, String> record =
                new ProducerRecord<>("email-validation-topic",message);
        kafkaTemplate.send(record);
    }
}