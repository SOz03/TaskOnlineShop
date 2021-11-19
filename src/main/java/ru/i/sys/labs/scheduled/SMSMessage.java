package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Order;

import java.util.List;

@Slf4j
@Component
public class SMSMessage implements Message {

    private final AlertMessagesService service;

    @Autowired
    public SMSMessage(AlertMessagesService schedulerService) {
        this.service = schedulerService;
    }

    @Override
    public void sendMessage() {
        List<Order> ordersNoPay = service.findListNoPay();

        for (Order order : ordersNoPay) {
            log.warn("SMS-message || Order with ID {} no pair", order.getId());
        }

        messagePaid();
    }

    private void messagePaid() {
        List<Order> ordersPay = service.findListPaid();
        for (Order order : ordersPay) {
            log.info("SMS-message || Order PAID with ID {} ", order.getId());
        }
    }
}
