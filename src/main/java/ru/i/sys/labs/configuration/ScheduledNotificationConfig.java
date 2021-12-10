package ru.i.sys.labs.configuration;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.i.sys.labs.notifications.NotificationsProperty;
import ru.i.sys.labs.notifications.sender.Sender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableConfigurationProperties(NotificationsProperty.class)
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledNotificationConfig {

    private final NotificationsProperty property;
    private final List<Sender> listSenders;

    @Bean
    public Map<String, Sender> getMapAllSender() {
        Map<String, Sender> mapLinks = new HashMap<>();
        listSenders.forEach(sender -> mapLinks.put(sender.getClass().getSimpleName(), sender));

        return mapLinks;
    }

    @Bean
    public List<Sender> getListEnabledChannels() {
        List<Sender> sendersEnabled = new ArrayList<>();
        property.getChannels()
                .entrySet()
                .stream()
                .filter(channel -> channel.getValue().isEnabled())
                .map(channel -> getMapAllSender().get(channel.getKey()))
                .forEach(sendersEnabled::add);

        return sendersEnabled;
    }
}
