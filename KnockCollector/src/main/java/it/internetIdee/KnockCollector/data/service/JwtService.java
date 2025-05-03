package it.internetIdee.KnockCollector.data.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import it.internetIdee.KnockCollector.data.entity.Agent;

import javax.crypto.SecretKey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final String SECRET = "super-secret-jwt-key-which-must-be-long-enough";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Agent agent) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", agent.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(agent.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 ore
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token, String email) {
        return extractUsername(token).equals(email) && !getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
