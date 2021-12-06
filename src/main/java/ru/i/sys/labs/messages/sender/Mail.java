package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import ru.i.sys.labs.messages.MailingService;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.application.mailing.messages.mail.enabled", havingValue = "true", matchIfMissing = true)
public class Mail implements Sender {

    private final MailingService mailingService;
    private final String className = Mail.class.getSimpleName();

    @Override
    @Scheduled(cron = "${spring.application.mailing.messages.mail.time}")
    public void sendMessage() {
        mailingService.send(className, mailingService.getListForCurrentMailing(className));
    }

}
