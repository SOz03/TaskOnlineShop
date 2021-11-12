package ru.i.sys.labs.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.scheduled.property")
public class Property {

    private String messageStyle;

    public String getMessageStyle() {
        return messageStyle;
    }

    public void setMessageStyle(String messageStyle) {
        this.messageStyle = messageStyle;
    }
}
