package ru.i.sys.labs.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.messages.property.Property;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.notification.enabled", havingValue = "true", matchIfMissing = true)
@Service
public class NotificationService {

    private final Property property;

    public void send(String prefix, List<Map<String, String>> mapList) {
        if (property.getNotifications().isEmpty()) {
            log.warn("No notifications, put 'enabled' in off mode");
        } else if (property.isEnabled()) {
            verifyMessage(prefix, mapList);
        }
    }

    public List<Map<String, String>> getList(String validation) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Map<String, String> map : property.getNotifications()) {
            if (map.get("enabled").equals("true")) {
                String[] arrayHowSend = map.get("how").split(property.getSplit());
                for (String how : setWay(arrayHowSend)) {
                    if (how.equals(validation)) {
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

    private void verifyMessage(String prefix, List<Map<String, String>> mapList) {
        if (!mapList.isEmpty()) {
            for (Map<String, String> map : mapList) {
                log.info("{} {}, {}", prefix, map.get("name"), map.get("description"));
            }
        } else {
            log.warn("List notifications is empty");
        }
    }

    private Set<String> setWay(String[] ways) {
        return Arrays.stream(ways).collect(Collectors.toSet());
    }

}
