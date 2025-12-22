package com.example.gmt_auth.domain.mail.repository;

import com.example.gmt_auth.domain.mail.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    Optional<EmailEntity> findTopByEmailOrderByExpiredAtDesc(String email);
}
