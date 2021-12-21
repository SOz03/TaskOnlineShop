package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsService {

    private final NotificationsProperty property;

    public void sendNotification(@NotEmpty String aClass) {
        NotificationsProperty.Notification notification = property
                .getChannels()
                .get(aClass);

        if (notification == null) log.warn("Notification {} equals {}", aClass, null);
        else log.info("{} {}, {}", aClass, notification.getName(), notification.getDescription());
    }

    public boolean filterNotification(String className){
        boolean startFilter = true;
        NotificationsProperty.Notification notification = property.getChannels().get(className);

        if (!notification.getDayWeek().equalsIgnoreCase("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat(notification.getDayFormat(), Locale.ENGLISH);
            if (!notification.getDayWeek().equalsIgnoreCase(dateFormat.format(new Date()))){
                startFilter = false;
            }
        } else {
            log.info("Day week is empty -> {}", className);
        }

        return startFilter;
    }
}
