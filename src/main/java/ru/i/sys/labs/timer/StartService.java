package ru.i.sys.labs.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;

import java.util.Date;

@Service
public class StartService {
    private final SchedulerService scheduler;

    @Autowired
    public StartService(SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runPayOrderTimer() {
        Order order = new Order();
        TimerInfo info = new TimerInfo(3600000L, new Date(), 1);

        scheduler.schedule(PayOrderTimer.class, order, info);
    }
}
