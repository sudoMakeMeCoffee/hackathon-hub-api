package com.hackathon_hub.hackathon_hub_api.utils;

import com.hackathon_hub.hackathon_hub_api.dto.response.UserResponseDto;
import com.hackathon_hub.hackathon_hub_api.repository.UserRepository;
import com.hackathon_hub.hackathon_hub_api.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final String secret = "astringsecretatleastbitslonghsdjsjdsdjsbdjbscbsjscsc";
    private final UserService userService;

    public JwtUtil(UserService userService) {
        this.userService = userService;
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserResponseDto userResponseDto) {
        String email = extractEmail(token);
        return email.equals(userResponseDto.getEmail()) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails) {

        UserResponseDto user = userService.getUserByUsername(userDetails.getUsername());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UUID extractUserId(String token) {
        Claims claims = extractClaims(token);
        return claims.get("userId", UUID.class); // or Long.class depending on how you stored it
    }

    public String extractJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public String extractEmailFromCookie(HttpServletRequest request){
        String jwt = extractJwtFromCookie(request);
        return  extractEmail(jwt);
    }


    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}