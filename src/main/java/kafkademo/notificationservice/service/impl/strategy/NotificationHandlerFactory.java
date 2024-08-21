package kafkademo.notificationservice.service.impl.strategy;

import java.util.Map;
import kafkademo.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationHandlerFactory {
    private final Map<String, NotificationService> handlerMap;

    public NotificationService getNotificationService(String notificationType) {
        return handlerMap.get(notificationType);
    }
}
