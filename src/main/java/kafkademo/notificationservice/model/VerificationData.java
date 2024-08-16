package kafkademo.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationData {
    private String email;
    private Long chatId;
}
