package ru.i.sys.labs.notifications.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.notifications.NotificationsProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class Sms implements Sender {

    private final NotificationsProperty property;

    @Override
    public void sendNotification() {
        NotificationsProperty.Notification notification = property
                .getChannels()
                .get("sms");

        if (notification == null) {
            log.warn("Notification Sms equals null");
        } else {
            log.info("Sms {}, {}", notification.getName(), notification.getDescription());
        }
    }

    @Override
    public boolean checkingDateFilterActivity() {
        NotificationsProperty.Notification notification = property.getChannels().get("sms");

        return !notification.getDayWeek().equalsIgnoreCase("") &&
                !notification.getDayFormat().equalsIgnoreCase("");
    }

    @Override
    public void filterAndSendNotification(){
        NotificationsProperty.Notification notification = property.getChannels().get("sms");
        SimpleDateFormat dateFormat = new SimpleDateFormat(notification.getDayFormat(), Locale.ENGLISH);

        if(notification.getDayWeek().equalsIgnoreCase(dateFormat.format(new Date()))){
            sendNotification();
        }
    }

}
