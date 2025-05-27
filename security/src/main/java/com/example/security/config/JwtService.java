package com.example.security.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.*;

@Service
public class JwtService {

    private static final String SECRET_KEY = "c4466b63e530d845c7a395e3948b9f6cb9b2ab1b346f8c57e6a5dc83f6611a78";

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    //extract a single claim
    public <T>  T extractClaim(String token, Function<Claims , T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //generate token using userdetails
    public String generateToken(UserDetails userDetails){
         return generateToken(new HashMap<>(),userDetails);

    }


    //generate a token
    public String generateToken(
            Map<String,Object> extraClaims, UserDetails userDetails){

        return builder().claims(extraClaims).subject(userDetails.getUsername()).
                issuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).
                signWith(getSignInKey(), SignatureAlgorithm.HS256).
                compact();

    }


    //validate tokens
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token))
                ;
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token){
        return parser()
                .verifyWith((SecretKey) getSignInKey())//digitally  signs JWT
                .build()
                .parseUnsecuredClaims(token)
                .getPayload();
    }

    private Key getSignInKey(){
        byte[] keyBytes =  Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
