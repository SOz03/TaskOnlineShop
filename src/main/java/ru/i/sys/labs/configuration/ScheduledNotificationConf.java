package ru.i.sys.labs.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.i.sys.labs.messages.property.Property;

@Configuration
@Slf4j
@EnableConfigurationProperties(Property.class)
@EnableScheduling
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.notification.enabled", matchIfMissing = true)
public class ScheduledNotificationConf {

    private final Property property;

    @Bean
    public TaskScheduler taskScheduler() {
        int pool = Integer.parseInt(property.getPoolSize());
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(pool);
        return scheduler;
    }
}
