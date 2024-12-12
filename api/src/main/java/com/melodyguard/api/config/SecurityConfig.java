package com.melodyguard.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean // 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // for testing
            .csrf().disable()
            .authorizeRequests().anyRequest().permitAll();
            // .authorizeRequests()
            // .antMatchers("/api/users/**").permitAll() // Allow all requests to user API
            // .anyRequest().authenticated(); // Secure other endpoints
        return http.build();
    }
}
