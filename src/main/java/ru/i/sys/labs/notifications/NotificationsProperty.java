package ru.i.sys.labs.notifications;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Map;

@RefreshScope
@Getter
@Setter
@ConfigurationProperties(prefix = "application.notifications")
public class NotificationsProperty {

    private Map<String, Notification> channels;

    @Setter
    @Getter
    public static class Notification {

        private String name;

        private boolean enabled;

        private String description;

        private String dayWeek;

        private String dayFormat;
    }
}

