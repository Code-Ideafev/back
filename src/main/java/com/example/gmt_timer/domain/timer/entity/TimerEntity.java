package com.example.gmt_timer.domain.timer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TimerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeId;

    private long elapsedTime;

    public Long getTimeId() { // list 조회를 위한 타이머 개인 아이디
        return timeId;
    }

    public long getElapsedTime() { // 저장할 타이머
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}