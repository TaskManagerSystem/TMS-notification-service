package kafkademo.notificationservice.model;

import lombok.Data;

@Data
public class VerificationData {
    private String email;
    private String chatId;
    private boolean present;
}
