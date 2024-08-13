package kafkademo.notificationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram.bot")
@Component
public class BotProperties {
    private String name;
    private String token;
}
