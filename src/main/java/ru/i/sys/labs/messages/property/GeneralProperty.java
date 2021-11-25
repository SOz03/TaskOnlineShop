package ru.i.sys.labs.messages.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.application.notification")
public class GeneralProperty extends Property {

    @Value("${spring.application.notification.sms.prefix}")
    private String smsPrefix;

    @Value("${spring.application.notification.mail.prefix}")
    private String mailPrefix;

    @Value("${spring.application.notification.social.prefix}")
    private String socialPrefix;
}
