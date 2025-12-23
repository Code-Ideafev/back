package com.example.gmt_timer.domain.timer.service;

import com.example.gmt_timer.domain.timer.entity.TimerEntity;
import com.example.gmt_timer.domain.timer.repository.TimerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TimerService {


    private final TimerRepository timerRepository;

    private Long startTime;
    private Long endTime;

    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
        this.startTime = null;
        this.endTime = null;
    }

    public void recordStartTime() {
        startTime = System.currentTimeMillis();
        System.out.println("시작 시간:" + startTime);
    }

    public long recordEndTime() {
        try {
            if (startTime == null) {
                throw new IllegalStateException("시작 시간이 측정되지 않았어요!");
            }

            endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            TimerEntity timer = new TimerEntity();
            timer.setElapsedTime(elapsedTime);

            System.out.println("끝난 시간:" + elapsedTime);

            timerRepository.save(timer);

            startTime = null;
            endTime = null;

            return elapsedTime;

        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TimerEntity> getTimer() { // 타이머 기록 조회
        return timerRepository.findAll();
    }

    public String formatTime(long milliseconds) {
        long totalS = milliseconds / 1000;
        long h = totalS / 3600;
        long m = (totalS % 3600) / 60;
        long s = totalS % 60;

        StringBuilder time = new StringBuilder();

        if(h > 0){
            time.append(h).append("시간 ");
        }
        if(m > 0){
            time.append(m).append("분 ");
        }
        if(s > 0){
            time.append(s).append("초");
        }
        return time.toString().trim();
    }
}
