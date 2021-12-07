package ru.i.sys.labs.notifications.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.notifications.NotificationsService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.notifications.channels.mail.enabled", havingValue = "true", matchIfMissing = true)
public class Mail implements Sender {

    private final NotificationsService notificationsService;
    private final String className = Mail.class.getSimpleName();

    @Override
    @Scheduled(cron = "${application.notifications.channels.mail.time}")
    public void sendNotification() {
        notificationsService.sendNotification(className);
    }

}
