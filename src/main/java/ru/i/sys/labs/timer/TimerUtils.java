package ru.i.sys.labs.timer;

import org.quartz.*;
import ru.i.sys.labs.entity.Order;

import java.util.Date;

public class TimerUtils {

    public TimerUtils() {
    }

    public static JobDetail buildJobDetail(final Class jobClass, Order order, TimerInfo timerInfo) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), timerInfo);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(order.getId().toString())
                .setJobData(jobDataMap)
                .build();

    }

    public static Trigger buildTrigger(Order order, TimerInfo timerInfo){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMilliseconds(timerInfo.getTimeNotice());

        builder = builder.withRepeatCount(timerInfo.getRepetitions() - 1);

        return TriggerBuilder
                .newTrigger()
                .withIdentity(order.getId().toString())
                .withSchedule(builder)
                .startAt(new Date(timerInfo.getTimeStart().getTime() + timerInfo.getTimeNotice()) )
                .build();
    }
}
