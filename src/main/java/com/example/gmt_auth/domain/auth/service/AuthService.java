package com.example.gmt_auth.domain.auth.service;

import com.example.gmt_auth.domain.auth.dto.JoinDto;
import com.example.gmt_auth.domain.auth.dto.MeDto;
import com.example.gmt_auth.domain.auth.entity.UserEntity;
import com.example.gmt_auth.domain.auth.repository.UserRepository;
import com.example.gmt_auth.global.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public void join(JoinDto joinDto) {
        if (userRepository.findByUsername(joinDto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디");
        }

        UserEntity user = new UserEntity();
        user.setUsername(joinDto.getUsername());
        user.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        user.setEmail(joinDto.getEmail());

        userRepository.save(user);
    }

    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("아이디 없음"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        return jwtUtil.createJwt(username);
    }

    public void updateProfile(MeDto meDto, String username) {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setProfileImageUrl(meDto.getProfileImageUrl());

        userRepository.save(user);
    }

    public List<UserEntity> userList() {
        return userRepository.findAll();
    }
}