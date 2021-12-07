package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.messages.MailingService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.mailing.messages.vk.enabled", havingValue = "true", matchIfMissing = true)
public class Vk implements Sender {

    private final MailingService mailingService;
    private final String className = Vk.class.getSimpleName();

    @Override
    @Scheduled(cron = "${application.mailing.messages.vk.time}")
    public void sendMessage() {
        mailingService.send(className, mailingService.getListForCurrentMailing(className));
    }
}
