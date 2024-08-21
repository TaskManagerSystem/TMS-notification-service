package kafkademo.notificationservice.service;

import com.example.dto.NotificationData;

public interface NotificationService {
    void sendNotification(NotificationData notificationData);
}
