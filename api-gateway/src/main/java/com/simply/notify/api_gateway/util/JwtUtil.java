package com.simply.notify.api_gateway.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.simply.notify.api_gateway.dto.LoginRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET = "my-super-secret-key-my-super-secret-key";
	
	private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
	
	public String generateToken(LoginRequest request) {
		return Jwts.builder()
                .subject(request.getUserName())
                .claim("role", "ADMIN")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (15 * 60 * 1000) ))
                .signWith(key)
                .compact();	
		
	}

}
