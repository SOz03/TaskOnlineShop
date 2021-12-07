package ru.i.sys.labs.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailingService {

    private final Property property;

    public void send(String className, List<Property.Message> allMessagesThisMailing) {
        if (allMessagesThisMailing.isEmpty()) {
            log.warn("{} No mailing, put 'enabled' in off mode", className);
        } else {
            for (Property.Message message : allMessagesThisMailing) {
                log.info("{} {}, {}", className, message.getName(), message.getDescription());
            }
        }
    }

    public List<Property.Message> getListForCurrentMailing(String className) {
        List<Property.Message> allMessagesThisMailing = new ArrayList<>();
        for (Map.Entry<String, Property.Message> messageEntry : property.getMessages().entrySet()) {
            if (messageEntry.getKey().toLowerCase(Locale.ROOT).equals(className.toLowerCase())) {
                allMessagesThisMailing.add(messageEntry.getValue());
            }
        }

        return allMessagesThisMailing;
    }

}
