package kafkademo.notificationservice;

import kafkademo.notificationservice.kafka.KafkaProducer;
import kafkademo.notificationservice.service.EmailService;
import kafkademo.notificationservice.util.TextConstant;
import kafkademo.notificationservice.util.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final String botName;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private EmailService emailService;

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
        if (update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().getText().contains("/")) {
            String messageText = update.getMessage().getText();
            String[] parts = messageText.split(" ", 2);
            String command = parts[0];
            String email = parts.length > 1 ? parts[1] : null;
            String chatId = update.getMessage().getChatId().toString();
            log.info("Command: {} received from chatId: {}",
                    command, update.getMessage().getChatId());
            switch (command) {
                case "/start" -> sendMessage(chatId, TextConstant.WELCOME_MESSAGE);
                case "/link" -> handleLinkCommand(update, email);
                default -> sendMessage(chatId, TextConstant.INVALID_INPUT_WARNING);
            }
        }
    }

    public void sendMessage(String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
            log.info("Message successfully sent: {}", message.getText());
        } catch (TelegramApiException e) {
            log.error("Can't send message: {}", message.getText(), e);
        }
    }

    private void handleLinkCommand(Update update, String email) {
        if (email != null && EmailValidator.getInstance().isValid(email)) {
            Long chatId = update.getMessage().getChatId();
            String token = new TokenGenerator().generateVerificationToken();
            kafkaProducer.sendEmailToValidate(email, chatId, token);
        } else {
            log.error("Invalid input: {}", update.getMessage().getText());
            sendMessage(update.getMessage().getChatId().toString(),
                    TextConstant.INVALID_INPUT_WARNING);
        }
    }
}
