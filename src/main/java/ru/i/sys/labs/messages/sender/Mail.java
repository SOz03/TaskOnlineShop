package ru.i.sys.labs.messages.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.messages.MailingService;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.mailing.messages.mail.enabled", havingValue = "true", matchIfMissing = true)
public class Mail implements Sender {

    private final MailingService mailingService;
    private final String className = Mail.class.getSimpleName();

    @Override
    @Scheduled(cron = "${application.mailing.messages.mail.time}")
    public void sendMessage() {
        mailingService.send(className, mailingService.getListForCurrentMailing(className));
    }

}
