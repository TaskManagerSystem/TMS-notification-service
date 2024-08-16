package kafkademo.notificationservice.util;

import java.util.UUID;

public class TokenGenerator {

    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
