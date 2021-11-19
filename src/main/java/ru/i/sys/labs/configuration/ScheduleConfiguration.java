package ru.i.sys.labs.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.i.sys.labs.scheduled.BirthdayMessage;
import ru.i.sys.labs.scheduled.Message;

@Configuration
@Slf4j
@EnableConfigurationProperties(ScheduledSettings.class)
@EnableScheduling
@ConditionalOnProperty(name = "spring.application.message-settings.enabled", matchIfMissing = true)
public class ScheduleConfiguration {

    private final Message[] message;
    private final ScheduledSettings scheduledSettings;
    private final BirthdayMessage birthdayMessage;

    @Autowired
    public ScheduleConfiguration(Message[] message, ScheduledSettings scheduledSettings,
                                 BirthdayMessage birthdayMessage) {
        this.message = message;
        this.scheduledSettings = scheduledSettings;
        this.birthdayMessage = birthdayMessage;
    }

    @Scheduled(cron = "${spring.application.message-settings.time-birthday}")
    public void congratulate() {
        birthdayMessage.sendMessage();
    }

    @Scheduled(cron = "${spring.application.message-settings.time}")
    public void enabledNotification() {

        String[] messages = scheduledSettings.getScheduled().split(" ");
        for (String message : messages) {
            for (Message scheduler : this.message) {
                if (message.equals("all")) {
                    scheduler.sendMessage();
                } else if (message.equals(scheduler.getClass().getSimpleName())) {
                    scheduler.sendMessage();
                    break;
                }
            }
        }

    }
}
