package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Order;

import java.util.List;

@Slf4j
@Component
public class MailMessage implements Message {

    private final AlertMessagesService service;

    @Autowired
    public MailMessage(AlertMessagesService schedulerService) {
        this.service = schedulerService;
    }

    @Override
    public void messageForPay() {
        List<Order> ordersNoPay = service.findListNoPay();
        for (Order order : ordersNoPay) {
            log.warn("Mail-message || Order with ID {} no pair", order.getId());
        }
    }
}
