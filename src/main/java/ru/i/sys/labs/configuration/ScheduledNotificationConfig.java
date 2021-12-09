package ru.i.sys.labs.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.i.sys.labs.notifications.NotificationsProperty;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
@EnableConfigurationProperties(NotificationsProperty.class)
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledNotificationConfig {

    @Getter
    private final List<Sender> listSenders;

    @Bean
    public Map<String, Sender> getMapAllRealizationSender() {
        Map<String, Sender> mapLinks = new HashMap<>();
        for (Sender sender : listSenders) {
            mapLinks.put(sender.getClass().getSimpleName(), sender);
        }
        return mapLinks;
    }
}
