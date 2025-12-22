package com.example.gmt_auth.domain.mail.controller;

import com.example.gmt_auth.domain.auth.service.AuthService;
import com.example.gmt_auth.domain.mail.dto.EmailSendDto;
import com.example.gmt_auth.domain.mail.dto.EmailVerifyDto;
import com.example.gmt_auth.domain.mail.entity.EmailEntity;
import com.example.gmt_auth.domain.mail.repository.EmailRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final AuthService authService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmailCode(EmailSendDto emailSendDto) {

        authService.sendEmailAuthCode(emailSendDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verifyEmailCode(EmailVerifyDto emailVerifyDto) {

        authService.verifyEmailAuthCode(emailVerifyDto.getEmail(), emailVerifyDto.getCode());
        return ResponseEntity.ok().build();
    }
}
