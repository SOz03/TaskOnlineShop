package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsService {

    private final NotificationsProperty property;

    public void sendNotification(@NotEmpty String aClass) {
        NotificationsProperty.Notification notification = property
                .getChannels()
                .get(aClass.toLowerCase());

        if (notification == null) log.warn("Notification {} equals {}", aClass, null);
        else log.info("{} {}, {}", aClass, notification.getName(), notification.getDescription());
    }
}
