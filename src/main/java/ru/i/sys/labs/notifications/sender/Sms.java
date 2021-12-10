package ru.i.sys.labs.notifications.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.notifications.NotificationsService;

@Component
@RequiredArgsConstructor
public class Sms implements Sender {

    private final NotificationsService notificationsService;
    private final String className = Sms.class.getSimpleName();

    @Override
    public void sendNotification() {
        notificationsService.sendNotification(className);
    }

}
