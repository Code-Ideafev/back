package com.example.gmt_timer.domain.timer.repository;

import com.example.gmt_timer.domain.timer.entity.TimerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimerRepository extends JpaRepository<TimerEntity, Long> {
    List<TimerEntity> findByUser_Id(Long userId);
    List<TimerEntity> findByUser_Email(String email);
}