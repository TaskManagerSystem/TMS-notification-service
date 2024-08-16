package kafkademo.notificationservice.service;

import java.util.concurrent.ConcurrentHashMap;
import kafkademo.notificationservice.model.VerificationData;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {
    private final ConcurrentHashMap<String, VerificationData> verificationMap =
            new ConcurrentHashMap<>();

    public void saveVerificationCode(String token, VerificationData verificationData) {
        verificationMap.put(token, verificationData);
    }
}
