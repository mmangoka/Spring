package com.example.security.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import static io.jsonwebtoken.Jwts.*;

@Service
public class JwtService {

    private static final String SECRET_KEY = "";

    public String extractUsername(String token) {

        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts.builder()
                .setSigningKey(getSignInKey())//digitally  signs JWT
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
}
