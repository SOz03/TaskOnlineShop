package ru.i.sys.labs.messages.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.application.notification")
public class Property {

    private List<Map<String, String>> notifications;
    private boolean enabled;
    private String smsPrefix;
    private String mailPrefix;
    private String socialPrefix;
    private String poolSize;
    private String split;

}
