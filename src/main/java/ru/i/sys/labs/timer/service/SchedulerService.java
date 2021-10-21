package ru.i.sys.labs.timer.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.i.sys.labs.entity.Order;
import ru.i.sys.labs.timer.model.TimerInfo;

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

    public void deleteTimer(final String timerId) {
        try {
            scheduler.deleteJob(new JobKey(timerId));
            log.info("Timer with ID '{}' deleted",timerId);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public TimerInfo getRunningService(String timerId) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));

            if (jobDetail == null) {
                log.warn("Failed find timer info with ID '{}'", timerId);
                return null;
            }

            return (TimerInfo) jobDetail.getJobDataMap().get(timerId);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void updateTimerInfo(final String timerId, final TimerInfo timerInfo) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));

            if (jobDetail == null) {
                log.warn("Failed find timer info with ID '{}'", timerId);
                return;
            }

            jobDetail.getJobDataMap().put(timerId, timerInfo);
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
