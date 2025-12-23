package com.example.gmt_timer.domain.timer.repository;

import com.example.gmt_timer.domain.timer.entity.TimerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerRepository extends JpaRepository<TimerEntity, Long> {

}