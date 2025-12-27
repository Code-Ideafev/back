package com.example.gmt_timer.domain.timer.entity;

import com.example.gmt_auth.domain.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private long elapsedTime;

    private LocalDate recordDate;

    public void setId(Long id) {
        this.id = id;
    }
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}