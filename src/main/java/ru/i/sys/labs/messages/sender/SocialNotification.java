package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.messages.NotificationService;
import ru.i.sys.labs.messages.property.GeneralProperty;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.notification.enabled", havingValue = "true", matchIfMissing = true)
public class SocialNotification implements NotificationSender {

    private final NotificationService notificationService;
    private final GeneralProperty property;

    @Override
    @Scheduled(cron = "${spring.application.notification.social.time}")
    public void sendMessage() {
        notificationService.handler(property.getSocialPrefix(), "social");
    }
}
