package ru.i.sys.labs.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.i.sys.labs.messages.Property;

@Configuration
@Slf4j
@EnableConfigurationProperties(Property.class)
@EnableScheduling
@RequiredArgsConstructor
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
