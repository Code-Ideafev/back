package com.example.gmt_auth.domain.auth.controller;

import com.example.gmt_auth.domain.auth.dto.JoinDto;
import com.example.gmt_auth.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinDto joinDto) {
        authService.join(joinDto.getUsername(), joinDto.getPassword());

        return ResponseEntity.ok()
                .body(new ApiResponse("success", "회원가입 성공"));
    }

    record ApiResponse(String status, String message) {}
}
