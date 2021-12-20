package ru.i.sys.labs.notifications.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.notifications.NotificationsProperty;
import ru.i.sys.labs.notifications.NotificationsService;

@Component
@RequiredArgsConstructor
public class Vk implements Sender {

    private final NotificationsProperty property;
    private final NotificationsService notificationsService;
    private final String className = getClass().getSimpleName().toLowerCase();

    @Override
    public void sendNotification() {
        notificationsService.sendNotification(className);
    }

    @Override
    public void startTimeFiltering() {
        String dayFormat = property.getChannels().get(className).getDayFormat();

        if (notificationsService.filterNotification(dayFormat, className)) {
            sendNotification();
        }
    }
}
