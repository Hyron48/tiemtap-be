package com.platinum.timetapbe.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenConfig {
    @Value("${jwt.validity}")
    private long JwtTokenValidity;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtTokenValidity))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims =
                Jwts
                        .parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails){
        final String username = getUsernameFromToken(jwtToken);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    public String getUsernameFromToken(String jwtToken){
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public boolean isTokenExpired(String jwtToken){
        final Date expiration = getExpirationDateFromToken(jwtToken);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String jwtToken){
        return getClaimFromToken(jwtToken, Claims::getExpiration);
    }
}
