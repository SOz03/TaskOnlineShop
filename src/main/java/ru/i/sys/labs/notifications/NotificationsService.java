package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsService {

    private final NotificationsProperty notificationsProperty;

    public void sendNotification(@NotEmpty String className) {
        NotificationsProperty.Notification notification = notificationsProperty
                .getChannels()
                .get(className.toLowerCase());

        log.info("<{} {}, {}>", className, notification.getName(), notification.getDescription());
    }
}
