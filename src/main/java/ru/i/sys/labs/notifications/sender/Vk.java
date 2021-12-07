package ru.i.sys.labs.notifications.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.notifications.NotificationsService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.notifications.channels.vk.enabled", havingValue = "true", matchIfMissing = true)
public class Vk implements Sender {

    private final NotificationsService notificationsService;
    private final String className = Vk.class.getSimpleName();

    @Override
    @Scheduled(cron = "${application.notifications.channels.vk.time}")
    public void sendNotification() {
        notificationsService.sendNotification(className);
    }
}
