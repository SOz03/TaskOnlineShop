package ru.i.sys.labs.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.i.sys.labs.notifications.NotificationsProperty;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RefreshScope
@Configuration
@EnableConfigurationProperties(NotificationsProperty.class)
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledNotificationConfig {

    private final List<Sender> listAllSenders;

    @Bean
    public Map<String, Sender> MapAllSender() {

        Map<String, Sender> mapLinksSenders = new HashMap<>();
        if (listAllSenders.isEmpty()) {
            log.warn("List Senders is empty");
        } else {
            listAllSenders.forEach(sender ->
                    mapLinksSenders.put(sender.getClass().getSimpleName(), sender)
            );
        }

        return mapLinksSenders;
    }
}
