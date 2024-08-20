package kafkademo.notificationservice.service;

import kafkademo.notificationservice.model.NotificationData;

public interface NotificationService {
    void sendNotification(NotificationData notificationData);
}
