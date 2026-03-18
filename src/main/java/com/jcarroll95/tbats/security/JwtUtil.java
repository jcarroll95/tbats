package com.jcarroll95.tbats.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

public class JwtUtil {


    // method to generate a token
    public String generateToken(String username, String role) {

        @Value
                expirationMs =
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    // method to read a username from a token

    // method to validate a token
}
