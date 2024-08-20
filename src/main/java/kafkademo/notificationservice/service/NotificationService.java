package kafkademo.notificationservice.service;

import kafkademo.notificationservice.model.MessageData;
import kafkademo.notificationservice.model.UserData;

public interface NotificationService {
    void sendNotification(UserData userData, MessageData messageData);
}
