package com.simply.notify.api_gateway.controller;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simply.notify.api_gateway.dto.LoginRequest;
import com.simply.notify.api_gateway.util.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
	
	private final JwtUtil jwtUtil;

    private final Key key =
            Keys.hmacShaKeyFor("my-super-secret-key-my-super-secret-key".getBytes());

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody LoginRequest request) {
    	
    	if (!"admin".equals(request.getUserName())
                || !"admin".equals(request.getPassword())) {
            return Mono.error(new RuntimeException("Invalid credentials"));
        }

        String token = jwtUtil.generateToken(request);

        return Mono.just(Map.of("token", token));
    }
}
