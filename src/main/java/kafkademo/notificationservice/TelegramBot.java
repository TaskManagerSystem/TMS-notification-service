package kafkademo.notificationservice;

import kafkademo.notificationservice.util.TextConstant;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final String botName;

    public TelegramBot(String botName, String botToken) {
        super(botToken);
        this.botName = botName;
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("/start")) {
                sendMessage(update, TextConstant.WELCOME_MESSAGE);
            }
            if (messageText.contains("/link")) {
                if (!messageText.contains(" ")) {
                    sendMessage(update, TextConstant.EMPTY_EMAIL_WARNING);
                } else {
                    String email = messageText.split(" ")[1];
                    sendMessage(update, TextConstant.EMAIL_RESPONSE.formatted(email));
                }
            }
        }
    }

    private void sendMessage(Update update, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Can't send message: {}", message.getText(), e);
        }
    }
}
