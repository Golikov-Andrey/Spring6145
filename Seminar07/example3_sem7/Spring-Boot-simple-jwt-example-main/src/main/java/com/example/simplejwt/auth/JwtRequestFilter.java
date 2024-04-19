package com.example.simplejwt.auth;

import com.example.simplejwt.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    @Autowired
    public JwtRequestFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    //Фильтр переопределяет проверку токена для spring
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ") ) {
                // Validate the JWT token
                //Декодирование токена
                String tokenWithoutBearer = token.substring(7);
                Authentication authentication = jwtTokenService.getAuthentication(tokenWithoutBearer);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
    }

    //Цепочка фильтров
    //Перенаправление от аутонтификации если есть токены
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //Фильтр до аутентификации
        http.addFilterBefore(
                this,
                UsernamePasswordAuthenticationFilter.class
        );

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated());

        //отключение CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
