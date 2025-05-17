package com.f1app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF if you're testing with Postman
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/public/**").permitAll() // Allow specific endpoints
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()); // Use basic auth
        return http.build();
    }

}
