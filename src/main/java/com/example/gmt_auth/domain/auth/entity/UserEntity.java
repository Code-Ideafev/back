package com.example.gmt_auth.domain.auth.entity;

import com.example.gmt_timer.domain.timer.entity.TimerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private String newPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimerEntity> timers = new ArrayList<>();

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
    public void setNewPassword(String newPassword) {this.newPassword = newPassword;}
}
