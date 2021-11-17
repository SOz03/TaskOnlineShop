package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Order;

import java.util.List;

//TODO ++ не оставляй закоментированных строк без информирования когда это раскоментировать.
// если тебе не нужен этот код, то никому другому он тоже не нужен
@Slf4j
@Component
public class SMSMessage implements Message {

    private final AlertMessagesService service;

    @Autowired
    public SMSMessage(AlertMessagesService schedulerService) {
        this.service = schedulerService;
    }

    @Override
    public void messageForPay() {
        List<Order> ordersNoPay = service.findListPaid();
        for (Order order : ordersNoPay) {
            log.warn("SMS-message || Order with ID {} no pair", order.getId());
        }

        messagePaid();
    }

    private void messagePaid() {
        List<Order> ordersPay = service.findListNoPay();
        for (Order order : ordersPay) {
            log.warn("SMS-message || Order PAID with ID {} ", order.getId());
        }
    }
}
