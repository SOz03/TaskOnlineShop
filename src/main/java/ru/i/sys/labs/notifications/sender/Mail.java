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
public class Mail implements Sender {

    private final NotificationsProperty property;

    @Override
    public void sendNotification() {
        NotificationsProperty.Notification notification = property
                .getChannels()
                .get("mail");

        if (notification == null) {
            log.warn("Notification Mail equals null");
        } else {
            log.info("Mail {}, {}", notification.getName(), notification.getDescription());
        }
    }

    @Override
    public boolean checkingDateFilterActivity() {
        NotificationsProperty.Notification notification = property.getChannels().get("mail");

        if(notification.getDayWeek().equalsIgnoreCase("") ||
                notification.getDayFormat().equalsIgnoreCase("")){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void filterAndSendNotification(){
        NotificationsProperty.Notification notification = property.getChannels().get("mail");
        SimpleDateFormat dateFormat = new SimpleDateFormat(notification.getDayFormat(), Locale.ENGLISH);

        if(notification.getDayWeek().equalsIgnoreCase(dateFormat.format(new Date()))){
            sendNotification();
        }
    }
}
