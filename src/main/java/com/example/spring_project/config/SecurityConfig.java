package com.example.spring_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //Tắt các tính năng tạo request giả mạo đến từ người dùng  để có thể nhận POST/PUT/DELETE
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() //allow all, don't require authentication
                );

        return http.build();
    }
}
