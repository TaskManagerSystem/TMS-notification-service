package kafkademo.notificationservice;

import kafkademo.notificationservice.kafka.KafkaProducer;
import kafkademo.notificationservice.service.EmailService;
import kafkademo.notificationservice.util.TextConstant;
import kafkademo.notificationservice.util.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final String botName;
    @Value("${base.url}")
    private String baseUrl;
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("/start")) {
                sendMessage(update, TextConstant.WELCOME_MESSAGE);
            }
            if (messageText.contains("/link")) {
                String[] parts = messageText.split(" ", 2);
                String email = parts.length > 1 ? parts[1] : null;
                if (email != null && EmailValidator.getInstance().isValid(email)) {
                    Long chatId = update.getMessage().getChatId();
                    String token = new TokenGenerator().generateVerificationToken(chatId, email);
                    String link = TextConstant.VERIFICATION_LINK.formatted(baseUrl, token);
                    kafkaProducer.sendEmailToValidate(email, chatId, token);
                    sendMessage(update, TextConstant.VERIFICATION_LINK_SENT);
                    emailService.sendVerificationCode(email, link);
                } else {
                    sendMessage(update, TextConstant.INVALID_EMAIL_WARNING);
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
