package com.example.gmt_auth.domain.mail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String code;
    private LocalDateTime expiredAt;
    private boolean verified;

    public void setEmail(String email) {
        this.email = email;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
