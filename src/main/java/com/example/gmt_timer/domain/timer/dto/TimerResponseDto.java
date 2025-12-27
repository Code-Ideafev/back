package com.example.gmt_timer.domain.timer.dto;

import com.example.gmt_timer.domain.timer.entity.TimerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimerResponseDto {

    private Long id;
    private Long elapsedTime;
    private String email;
    private LocalDate recordDate;

    public TimerResponseDto(TimerEntity timer) {
        this.id = timer.getId();
        this.elapsedTime = timer.getElapsedTime();
        this.email = timer.getUser().getEmail();
        this.recordDate = timer.getRecordDate();
    }
}
