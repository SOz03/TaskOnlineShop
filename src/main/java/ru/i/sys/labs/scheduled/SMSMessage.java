package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Order;

import java.util.List;

//@ConditionalOnProperty(prefix = "spring.application.scheduled", name = "class", havingValue = "two")
@Slf4j
@Component
public class SMSMessage implements SchedulerMessage {

    private final SchedService service;

    @Autowired
    public SMSMessage(SchedService schedulerService) {
        this.service = schedulerService;
    }

    @Override
    public void messageForPay() {
        List<Order> ordersNoPay = service.findListNoPay();
        for (Order order : ordersNoPay) {
            log.warn("SMS-message || Order with ID {} no pair", order.getId());
        }
    }
}
