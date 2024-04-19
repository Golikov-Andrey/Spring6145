package com.example.simplejwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenService implements Serializable {

    @Value("${jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY;

    @Value("${jwt.secret}")
    private String secret;

    public int getUserIdFromToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String tokenWithoutBearer = token.substring(7);
        return Integer.parseInt(getClaimFromToken(tokenWithoutBearer, Claims::getSubject));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(int userId, String role) {
//      Any claim can be added if needed
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // Add a claim

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) //* 1000 to turn millis into seconds
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        int userId = Integer.parseInt(claims.getSubject());
        String role = claims.get("role", String.class);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // Add the "ROLE_" prefix if using Spring Security

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }
}
