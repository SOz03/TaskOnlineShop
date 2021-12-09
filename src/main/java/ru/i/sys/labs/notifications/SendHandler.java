package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendHandler {

    private final Map<String, Sender> getMapAllRealizationSender;

    @Scheduled(cron = "${application.notifications.time}")
    private void handlerAllSenders() {
        if (!getMapAllRealizationSender.isEmpty()) {
            for (Map.Entry<String, Sender> linkSender : getMapAllRealizationSender.entrySet()) {
                linkSender.getValue().sendNotification();
            }
        }
    }
}
