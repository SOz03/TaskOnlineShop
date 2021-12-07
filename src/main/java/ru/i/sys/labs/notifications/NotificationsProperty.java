package ru.i.sys.labs.notifications;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.notifications")
public class NotificationsProperty {

    private Map<String, Notification> channels = new HashMap<>();

    @Setter
    @Getter
    public static class Notification {

        private String name;

        private boolean enabled;

        private String description;
    }
}

