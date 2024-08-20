package kafkademo.notificationservice.service;

public interface VerificationService {
    void sendVerification(String recipientEmail, String link);
}
