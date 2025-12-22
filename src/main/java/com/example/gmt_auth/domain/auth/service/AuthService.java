package com.example.gmt_auth.domain.auth.service;

import com.example.gmt_auth.domain.auth.dto.JoinDto;
import com.example.gmt_auth.domain.auth.dto.MeDto;
import com.example.gmt_auth.domain.auth.entity.UserEntity;
import com.example.gmt_auth.domain.auth.repository.UserRepository;
import com.example.gmt_auth.domain.mail.entity.EmailEntity;
import com.example.gmt_auth.domain.mail.repository.EmailRepository;
import com.example.gmt_auth.domain.mail.service.EmailService;
import com.example.gmt_auth.global.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final EmailRepository emailAuthRepository;
    private final EmailService emailService;

    private static final SecureRandom secureRandom = new SecureRandom();

    public void join(JoinDto joinDto) {

        EmailEntity auth = emailAuthRepository
                .findTopByEmailOrderByExpiredAtDesc(joinDto.getEmail())
                .orElseThrow(() -> new RuntimeException("이메일 인증 필요"));
        if (!auth.isVerified()) {
            throw new RuntimeException("이메일 인증 미완료");
        }
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

    public void sendEmailAuthCode(String email) {
        String code = generateCode();

        EmailEntity auth = new EmailEntity();
        auth.setEmail(email);
        auth.setCode(code);
        auth.setExpiredAt(LocalDateTime.now().plusMinutes(5));
        auth.setVerified(false);

        emailAuthRepository.save(auth);

        emailService.sendAuthCode(email, code);
    }

    public void verifyEmailAuthCode(String email, String code) {
        EmailEntity auth = emailAuthRepository
                .findTopByEmailOrderByExpiredAtDesc(email)
                .orElseThrow(() -> new RuntimeException("인증 요청 없음"));

        if (auth.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("인증번호 만료");
        }

        if (!auth.getCode().equals(code)) {
            throw new RuntimeException("인증번호 불일치");
        }

        auth.setVerified(true);
        emailAuthRepository.save(auth);
    }

    private String generateCode() {
        int code = secureRandom.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}