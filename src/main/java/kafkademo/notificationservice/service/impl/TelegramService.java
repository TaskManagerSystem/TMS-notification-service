package kafkademo.notificationservice.service.impl;

import kafkademo.notificationservice.TelegramBot;
import kafkademo.notificationservice.model.MessageData;
import kafkademo.notificationservice.model.UserData;
import kafkademo.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TELEGRAM")
@RequiredArgsConstructor
public class TelegramService implements NotificationService {
    private final TelegramBot telegramBot;

    @Override
    public void sendNotification(UserData userData, MessageData messageData) {
        String telegramMessage = formMessageForTelegram(messageData.getText());
        telegramBot.sendMessage(userData.getChatId().toString(),telegramMessage);
        //TODO: logic for forming and sending notification via telegram
    }

    private String formMessageForTelegram(String message) {
        //TODO: logic for telegram message forming
        return null;
    }
}
