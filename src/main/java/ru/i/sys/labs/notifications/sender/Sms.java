package ru.i.sys.labs.notifications.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.notifications.NotificationsProperty;
import ru.i.sys.labs.notifications.NotificationsService;

@Component
@RequiredArgsConstructor
public class Sms implements Sender {

    private final NotificationsService notificationsService;
    private final String className = getClass().getSimpleName().toLowerCase();

    @Override
    public void sendNotification() {
        if (checkingFilterIsEnabled()) notificationsService.sendNotification(className);
    }

    @Override
    public boolean checkingFilterIsEnabled() {
         return notificationsService.filterNotification(className);
    }

}
