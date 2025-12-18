package com.example.gmt_auth.global.jwt;

import com.example.gmt_auth.domain.auth.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public JWTFilter(JWTUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String uri = request.getRequestURI();
        if (uri.startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        if (jwtUtil.isExpired(token)) {
            chain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(request, response);
    }
}