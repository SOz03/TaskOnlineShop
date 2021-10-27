package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Order;

import java.util.List;

@ConditionalOnProperty(prefix = "spring.application.scheduled", name = "class", havingValue = "one")
@Slf4j
@Component
public class OneScheduler implements SchedulerLogic {

    private final SchedService service;

    @Autowired
    public OneScheduler(SchedService schedulerService) {
        this.service = schedulerService;
    }

    @Scheduled(cron = "${spring.application.scheduled.time}")
    @Override
    public void messageForPay() {
        List<Order> ordersNoPay = service.findListNoPay();
        for (Order order : ordersNoPay) {
            log.warn("ONE Заказ с ID {} не оплачен", order.getId());
        }
    }
}
