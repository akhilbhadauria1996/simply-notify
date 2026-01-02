package com.simply.notify.api_gateway.controller;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Key key =
            Keys.hmacShaKeyFor("my-super-secret-key-my-super-secret-key".getBytes());

    @PostMapping("/login")
    public Map<String, String> login() {

        String token = Jwts.builder()
                .subject("akhil")
                .claim("role", "USER")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();

        return Map.of("token", token);
    }
}
