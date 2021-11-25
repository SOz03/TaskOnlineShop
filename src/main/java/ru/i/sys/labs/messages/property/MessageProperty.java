package ru.i.sys.labs.messages.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.application.notification.send.message")
public class MessageProperty extends Property {

    private String text;
    private String recipient;
}
