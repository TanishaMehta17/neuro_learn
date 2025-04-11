package com.example.neuro_learn.neuro_learn.Config;

import java.util.Base64;
import java.util.Date;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    // Replace this with your securely generated Base64 key
    private String secret = "adFZkHMBJGxaNmJICM6elvhqYjRYdC5jmbpr5OiEx8g=";

    private byte[] secretKeyBytes;

    @jakarta.annotation.PostConstruct
    public void init() {
        // Decode the Base64 secret to get the byte[] key
        secretKeyBytes = Base64.getDecoder().decode(secret);
    }

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)) // 5 hours
            .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(secretKeyBytes)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
            .setSigningKey(secretKeyBytes)
            .parseClaimsJws(token)
            .getBody()
            .getExpiration()
            .before(new Date());
    }
}
