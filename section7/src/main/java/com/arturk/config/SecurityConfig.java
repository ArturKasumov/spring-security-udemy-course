package com.arturk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint customBasicAuthenticationEntryPoint;
    private final AccessDeniedHandler customAccessDeniedHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session.sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId))

                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/notices", "/contact", "/customer/register", "/error").permitAll()
                                .anyRequest().authenticated()
                )

                .formLogin(withDefaults())

                .httpBasic(configurer ->
                        configurer.authenticationEntryPoint(customBasicAuthenticationEntryPoint))

                .exceptionHandling(configurer ->
                        configurer.accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
