package ru.i.sys.labs.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.i.sys.labs.entity.Order;

import java.util.List;

@ConditionalOnProperty(prefix = "spring.application.scheduled", name = "class", havingValue = "two")
@Slf4j
@Component
public class TwoScheduler implements SchedulerLogic {

    private final SchedService service;

    @Autowired
    public TwoScheduler(SchedService schedulerService) {
        this.service = schedulerService;
    }

    @Scheduled(cron = "${spring.application.scheduled.time}")
    @Override
    public void messageForPay() {
        List<Order> ordersNoPay = service.findListNoPay();
        for (Order order : ordersNoPay) {
            log.warn("TWO Заказ с ID {} не оплачен", order.getId());
        }
    }
}
