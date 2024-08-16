package kafkademo.notificationservice.config;

import kafkademo.notificationservice.TelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BotConfig {
    private final BotProperties botProperties;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot telegramBot =
                new TelegramBot(botProperties.getName(), botProperties.getToken());
        try {
            var botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            log.error("Can't register bot: {}", e.getMessage(), e);
        }
        return telegramBot;
    }
}
