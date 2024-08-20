package kafkademo.notificationservice.service.impl;

import kafkademo.notificationservice.TelegramBot;
import kafkademo.notificationservice.model.NotificationData;
import kafkademo.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TELEGRAM")
@RequiredArgsConstructor
public class TelegramService implements NotificationService {
    private final TelegramBot telegramBot;

    @Override
    public void sendNotification(NotificationData notificationData) {
        String telegramMessage = formMessageForTelegram(notificationData.getMessageText());
        telegramBot.sendMessage(notificationData.getChatId().toString(),telegramMessage);
        //TODO: logic for forming and sending notification via telegram
    }

    private String formMessageForTelegram(String message) {
        //TODO: logic for telegram message forming
        return null;
    }
}
