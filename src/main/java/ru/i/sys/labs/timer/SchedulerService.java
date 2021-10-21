package ru.i.sys.labs.timer;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
public class SchedulerService {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, Order order, TimerInfo timerInfo) {
        final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, order, timerInfo);
        final Trigger trigger = TimerUtils.buildTrigger(order, timerInfo);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void start() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void stop() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

}
