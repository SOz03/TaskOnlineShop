package ru.i.sys.labs.messages;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.mailing")
public class Property {

    private Map<String, Message> messages = new HashMap<>();

    @Size(min = 1, max = 2)
    @Min(value = 1, message = "Minimum number of threads 1")
    private String poolSize;

    @Setter
    @Getter
    public static class Message {

        private String name;

        private boolean enabled;

        private String description;
    }
}

