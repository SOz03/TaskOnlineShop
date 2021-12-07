package ru.i.sys.labs.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.i.sys.labs.notifications.NotificationsProperty;

@Configuration
@Slf4j
@EnableConfigurationProperties(NotificationsProperty.class)
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledNotificationConf {

}
