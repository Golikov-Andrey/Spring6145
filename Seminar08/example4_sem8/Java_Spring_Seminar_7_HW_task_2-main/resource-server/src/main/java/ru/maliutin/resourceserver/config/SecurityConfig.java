package ru.maliutin.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация сервера ресурсов.
 */
@Configuration
public class SecurityConfig {
    /**
     * Правила фильтрации.
     * @param http защищенный http запрос.
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                // требование аутентификации всех запросов
                .anyRequest().authenticated())
                // с использованием jwt токена
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));
        return http.build();
    }

}
