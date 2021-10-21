package ru.i.sys.labs.timer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.i.sys.labs.timer.model.TimerInfo;
import ru.i.sys.labs.timer.service.SchedulerService;

@RestController
@RequestMapping("/api/shop/timers")
public class TimerController {

    private final SchedulerService schedulerService;

    @Autowired
    public TimerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("{timerId}")
    public TimerInfo getTimerInfo(@PathVariable String timerId){
        return schedulerService.getRunningService(timerId);
    }
}
