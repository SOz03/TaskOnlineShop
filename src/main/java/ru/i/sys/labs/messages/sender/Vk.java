package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import ru.i.sys.labs.messages.MailingService;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.mailing.messages.vk.enabled", havingValue = "true", matchIfMissing = true)
public class Vk implements Sender {

    private final MailingService mailingService;
    private final String className = Vk.class.getSimpleName();

    @Override
    @Scheduled(cron = "${spring.application.mailing.messages.vk.time}")
    public void sendMessage() {
        mailingService.send(className, mailingService.getListForCurrentMailing(className));
    }
}
