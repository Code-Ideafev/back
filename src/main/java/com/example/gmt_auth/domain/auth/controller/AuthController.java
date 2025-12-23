package com.example.gmt_auth.domain.auth.controller;

import com.example.gmt_auth.domain.auth.dto.JoinDto;
import com.example.gmt_auth.domain.auth.dto.MeDto;
import com.example.gmt_auth.domain.auth.dto.ResetPasswordDto;
import com.example.gmt_auth.domain.auth.entity.UserEntity;
import com.example.gmt_auth.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> join(@RequestBody JoinDto joinDto) {
        authService.join(joinDto);

        return ResponseEntity.ok()
                .body(new ApiResponse("success", "회원가입 성공"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        authService.resetPassword(resetPasswordDto.getEmail(), resetPasswordDto.getNewPassword());

        return ResponseEntity.ok()
                .body(new ApiResponse("success", "비밀번호가 재설정되었습니다"));
    }

    record ApiResponse(String status, String message) {}

    @PutMapping("/me/profile")
    public ResponseEntity<Void> updateProfile(
            @RequestBody MeDto meDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        authService.updateProfile(meDto, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/list")
    public List<UserEntity> findAll() {
        return authService.userList();
    }
}