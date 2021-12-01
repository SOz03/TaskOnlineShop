package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.messages.NotificationService;
import ru.i.sys.labs.messages.property.Property;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.notification.enabled", havingValue = "true", matchIfMissing = true)
public class Social implements Sender {

    private final NotificationService notificationService;
    private final Property property;

    @Override
    @Scheduled(cron = "${spring.application.notification.social.time}")
    public void sendMessage() {
        List<Map<String, String>> socialList = notificationService.getList(property.getSocialValidation());
        notificationService.send(property.getSocialPrefix(), socialList);
    }
}
