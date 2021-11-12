package ru.i.sys.labs.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.scheduled.property")
public class Notification {

    private String selectStyleMessage;

    public String getSelectStyleMessage() {
        return selectStyleMessage;
    }

    public void setSelectStyleMessage(String selectStyleMessage) {
        this.selectStyleMessage = selectStyleMessage;
    }
}
