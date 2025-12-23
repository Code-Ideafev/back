package com.example.gmt_timer.domain.timer.controller;

import com.example.gmt_timer.domain.timer.entity.TimerEntity;
import com.example.gmt_timer.domain.timer.service.TimerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timer")
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/startTime")
    public String startTimer(){
        timerService.recordStartTime();
        return "공부 시작!";
    }

    @GetMapping("/endTime")
    public String endTimer(){
        long elapsedTime = timerService.recordEndTime();
        String realTime = timerService.formatTime(elapsedTime);
        return "공부 완료! " + realTime + "동안 공부를 하였어요!";
    }

    @GetMapping("/list")
    public List<TimerEntity> list(){
        return timerService.getTimer();
    }
}