package kafkademo.notificationservice.util;

import java.util.concurrent.ThreadLocalRandom;

public class CodeGenerator {
    public static String generateVerificationCode() {
        int code = ThreadLocalRandom.current().nextInt(100000, 999999);
        return String.valueOf(code);
    }
}
