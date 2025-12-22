package com.example.gmt_auth.domain.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@NoArgsConstructor
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String profileImageUrl;
    private String code;
    private LocalDateTime expiredAt;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setProfileImageUrl(String profileImageUrl) {this.profileImageUrl = profileImageUrl;}
    public void setCode(String code) {this.code = code;}
    public void setExpiredAt(LocalDateTime expiredAt) {this.expiredAt = expiredAt;}
}
