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
public class Vk implements Sender {

    private final NotificationsProperty property;
    private NotificationsProperty.Notification notification;

    @Override
    public void sendNotification() {
        notification = property.getChannels().get("vk");
        if (notification == null) {
            log.warn("Notification Vk equals null");
        } else {
            log.info("Vk {}, {}", notification.getName(), notification.getDescription());
        }
    }

    @Override
    public boolean filteringNotification() {
        notification = property.getChannels().get("vk");
        if(!notification.getDayWeek().equalsIgnoreCase("") ){
            if(!notification.getDayFormat().equalsIgnoreCase("")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(notification.getDayFormat(), Locale.ENGLISH);
                return notification.getDayWeek().equalsIgnoreCase(dateFormat.format(new Date()));
            } else {
                log.warn("Vk date format is empty");
            }
        } else if(notification.getDayFormat().equalsIgnoreCase("")){
            return true;
        } else {
            log.warn("Vk day week is empty");
        }
        return false;
    }
}
