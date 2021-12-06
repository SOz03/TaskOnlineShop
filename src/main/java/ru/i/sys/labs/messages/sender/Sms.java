package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import ru.i.sys.labs.messages.MailingService;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.mailing.messages.sms.enabled", havingValue = "true", matchIfMissing = true)
public class Sms implements Sender {

    private final MailingService mailingService;
    private final String className = Sms.class.getSimpleName();

    @Override
    @Scheduled(cron = "${spring.application.mailing.messages.sms.time}")
    public void sendMessage() {
        mailingService.send(className, mailingService.getListForCurrentMailing(className));
    }

}
