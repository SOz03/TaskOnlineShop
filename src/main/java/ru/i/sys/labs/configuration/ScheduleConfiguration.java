package ru.i.sys.labs.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.i.sys.labs.scheduled.OneScheduler;
import ru.i.sys.labs.scheduled.TwoScheduler;

@Configuration
@Slf4j
@EnableConfigurationProperties(Notification.class)
@EnableScheduling
@ConditionalOnProperty(name = "spring.application.scheduled.enabled", matchIfMissing = true)
public class ScheduleConfiguration {

    private final OneScheduler one;
    private final TwoScheduler two;
    private final Notification notification;

    @Autowired
    public ScheduleConfiguration(OneScheduler oneScheduler, TwoScheduler twoScheduler, Notification notification) {
        this.one = oneScheduler;
        this.two = twoScheduler;
        this.notification = notification;
    }

    @Scheduled(cron = "${spring.application.scheduled.time}")
    public void enabledNotification(){
        switch (notification.getSelectStyleMessage()) {
            case "all":
                one.messageForPay();
                two.messageForPay();
                break;
            case "one":
                one.messageForPay();
                break;
            case "two":
                two.messageForPay();
                break;
        }
    }
}
