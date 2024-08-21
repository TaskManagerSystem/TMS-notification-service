package kafkademo.notificationservice.service.impl;

import kafkademo.notificationservice.TelegramBot;
import kafkademo.notificationservice.model.NotificationData;
import kafkademo.notificationservice.service.NotificationService;
import kafkademo.notificationservice.util.TextConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("TELEGRAM")
@RequiredArgsConstructor
public class TelegramService implements NotificationService {
    private final TelegramBot telegramBot;

    @Override
    public void sendNotification(NotificationData notificationData) {
        String telegramMessage = formMessageForTelegram(notificationData);
        telegramBot.sendMessage(notificationData.getChatId().toString(),telegramMessage);
    }

    private String formMessageForTelegram(NotificationData notificationData) {
        return TextConstant.TELEGRAM_NOTIFICATION.formatted(
                notificationData.getMessageSubject(), notificationData.getMessageText()
        );
    }
}
