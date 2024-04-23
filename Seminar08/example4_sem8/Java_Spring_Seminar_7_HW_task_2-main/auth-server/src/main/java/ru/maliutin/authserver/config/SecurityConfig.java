package ru.maliutin.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Конфигурация сервера авторизации
 */
@Configuration
public class SecurityConfig {
    /**
     * Создание пользователя для проверки.
     * @return созданного пользователя.
     */
    @Bean
    UserDetailsService inMemoryUserDetailsManager(){
        var userBuilder = User.builder();
        UserDetails user = userBuilder
                .username("user")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Отключение шифрования пароля (только в учебных целях)
     * @return объект PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
