package ru.i.sys.labs.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "spring.application.scheduled.enabled", matchIfMissing = true)
public class ScheduleConfiguration {
}
