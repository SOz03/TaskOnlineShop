package ru.i.sys.labs.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.i.sys.labs.messages.property.*;

@Configuration
@Slf4j
@EnableConfigurationProperties({GeneralProperty.class,
        MessageProperty.class, OrdersPaidProperty.class,
        OrdersUnpaidProperty.class, HappyBirthdayProperty.class
})
@EnableScheduling
@ConditionalOnProperty(name = "spring.application.notification.enabled", matchIfMissing = true)
public class ScheduledNotificationConf {

}
