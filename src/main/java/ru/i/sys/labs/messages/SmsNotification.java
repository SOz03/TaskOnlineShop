package ru.i.sys.labs.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.messages.property.GeneralProperty;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.notification.enabled", havingValue = "true", matchIfMissing = true)
public class SmsNotification implements NotificationSender {

    private final NotificationService notificationService;
    private final GeneralProperty property;

    @Override
    @Scheduled(cron = "${spring.application.notification.sms.time}")
    public void sendMessage() {
        notificationService.handler(property.getSmsPrefix(), "sms");
    }

}
