package ru.i.sys.labs.notifications;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.notifications.sender.Sender;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsService {

    private final List<Sender> listSenders;
    private final NotificationsProperty property;

    public void sendNotification(@NotEmpty String aClass) {
        NotificationsProperty.Notification notification = property
                .getChannels()
                .get(aClass);

        log.info("<{} {}, {}>", aClass, notification.getName(), notification.getDescription());
    }

    public List<Sender> getListEnabledChannels() {
        List<Sender> senders = new ArrayList<>();


        for (Map.Entry<String, NotificationsProperty.Notification> channel : property.getChannels().entrySet()) {
            for (Sender sender : listSenders) {
                if (channel.getKey().equals(sender.getClass().getSimpleName()) && channel.getValue().isEnabled()) {
                    senders.add(sender);
                }
            }
        }

        return senders;
    }

}
