package kafkademo.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmailToValidate(String email, String chatId) {
        ProducerRecord<String, String> record =
                new ProducerRecord<>("email-validation-topic", chatId, email);
        kafkaTemplate.send(record);
    }
}
