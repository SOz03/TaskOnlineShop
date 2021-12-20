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

    public boolean filterNotification(String dayFormat, String className){
        boolean startFilter = true;

        String dayWeekChannel = property.getChannels().get(className).getDayWeek();
        if (!dayWeekChannel.equals("")){
            Date nowDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(dayFormat, Locale.ENGLISH);
            if (!dayWeekChannel.equalsIgnoreCase(dateFormat.format(nowDate))){
                startFilter = false;
            }
        } else {
            log.info("Day format is empty -> {}", className);
        }

        return startFilter;
    }
}
