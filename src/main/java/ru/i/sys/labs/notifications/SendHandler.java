package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendHandler {

    private final NotificationsProperty property;
    private final Map<String, Sender> mapAllSender;


    @Scheduled(cron = "${application.notifications.time}")
    private void handlerAllSenders() {

        property.getChannels()
                .entrySet()
                .stream()
                .filter(channel -> channel.getValue().isEnabled())
                .map(channel -> mapAllSender.get(channel.getKey().toLowerCase()))
                .forEach(Sender::startTimeFiltering);
    }
}
