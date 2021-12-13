package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendHandler {

    private final NotificationsProperty property;
    private final Map<String, Sender> mapAllSender;
    private List<Sender> enabledSender = new ArrayList<>();

    @Scheduled(cron = "${application.notifications.time}")
    private void handlerAllSenders() {
        if (enabledSender.isEmpty()) {
            enabledSender = property.getChannels()
                    .entrySet()
                    .stream()
                    .filter(channel -> channel.getValue().isEnabled())
                    .map(channel -> mapAllSender.get(channel.getKey().toLowerCase()))
                    .collect(Collectors.toList());
        }

        enabledSender.forEach(Sender::sendNotification);
    }
}
