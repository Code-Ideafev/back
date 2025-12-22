package com.example.gmt_auth.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinDto {
    private String username;
    private String password;
    private String email;
}
