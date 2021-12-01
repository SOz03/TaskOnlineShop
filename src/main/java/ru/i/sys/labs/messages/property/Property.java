package ru.i.sys.labs.messages.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.application.notification")
public class Property {

    private List<Map<String, String>> notifications;

    private boolean enabled;

    @Value("${spring.application.notification.sms.prefix}")
    private String smsPrefix;

    @Value("${spring.application.notification.mail.prefix}")
    private String mailPrefix;

    @Value("${spring.application.notification.social.prefix}")
    private String socialPrefix;

    @Min(value = 1, message = "Minimum number of threads 1")
    private String poolSize;

    @NotEmpty
    private String split;

    @NotEmpty
    @Value("${spring.application.notification.sms.validation}")
    private String smsValidation;

    @NotEmpty
    @Value("${spring.application.notification.mail.validation}")
    private String mailValidation;

    @NotEmpty
    @Value("${spring.application.notification.social.validation}")
    private String socialValidation;

}
