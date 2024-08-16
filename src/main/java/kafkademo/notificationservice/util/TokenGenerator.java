package kafkademo.notificationservice.util;

import java.util.UUID;

public class TokenGenerator {

    public String generateVerificationToken(Long chatId, String email) {
        return UUID.randomUUID().toString();
    }
}
