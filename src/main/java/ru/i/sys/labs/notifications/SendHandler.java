package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.configuration.ScheduledNotificationConfig;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendHandler {

    private final ScheduledNotificationConfig scheduledNotificationConfig;

    @Scheduled(cron = "${application.notifications.time}")
    private void handlerAllSenders() {
        List<Sender> senders = scheduledNotificationConfig.getListEnabledChannels();

        if (!senders.isEmpty()) {
            for (Sender sender : senders) {
                sender.sendNotification();
            }
        } else log.warn("No enabled channels");

    }


}
