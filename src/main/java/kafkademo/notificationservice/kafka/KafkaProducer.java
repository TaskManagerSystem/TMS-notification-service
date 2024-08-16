package kafkademo.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmailToValidate(String email, Long chatId, String token) {
        String message = token + ":" + email + ":" + chatId;
        String topic = "email-validation-topic";
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic,message);
        kafkaTemplate.send(record);
        log.info("Sent record: {} to the topic {}", record, topic);
    }
}
