package com.maximus.insurance.customer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity          // Without this, Spring Security will not activate its filters.This helps Spring map your security configurations.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // This method returns a SecurityFilterChain, which defines how every HTTP request should be secured.

        http.csrf(AbstractHttpConfigurer::disable)
        //CSRF (Cross-Site Request Forgery) is a protection layer used mostly for web applications with sessions.
        // For REST APIs (JSON-based, stateless), CSRF is not needed.So we disable it.
        // Otherwise POST/PUT/DELETE may fail with 403 errors.

                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        //authorizeHttpRequests(...): This is where you define which API endpoints are allowed, blocked, or need authentication.
        // auth.anyRequest().permitAll(): Allow every request without any authentication.
        //So your entire application is open.
        //No login, no token, no authentication required.
        return http.build();
    }
}
