package ru.i.sys.labs.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.i.sys.labs.scheduled.SchedulerMessage;

@Configuration
@Slf4j
@EnableConfigurationProperties(Property.class)
@EnableScheduling
@ConditionalOnProperty(name = "spring.application.message-settings.enabled", matchIfMissing = true)
public class ScheduleConfiguration {

    private final SchedulerMessage[] schedulerMessage;
    private final Property property;

    @Autowired
    public ScheduleConfiguration(SchedulerMessage[] schedulerMessage, Property property) {
        this.schedulerMessage = schedulerMessage;
        this.property = property;
    }

    @Scheduled(cron = "${spring.application.message-settings.time}")
    public void enabledNotification() {

        String[] messages = property.getScheduled().split(" ");
        for (String message : messages) {
            for (SchedulerMessage scheduler : schedulerMessage) {
                if (message.equals("all")) {
                    scheduler.messageForPay();
                } else if (message.equals(scheduler.getClass().getSimpleName())) {
                    scheduler.messageForPay();
                    break;
                }
            }
        }

    }
}
