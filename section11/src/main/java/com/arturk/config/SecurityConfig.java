package com.arturk.config;

import com.arturk.config.filter.JwtValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint customBasicAuthenticationEntryPoint;
    private final AccessDeniedHandler customAccessDeniedHandler;
    private final JwtValidationFilter jwtValidationFilter;

    public static final List<String> PUBLIC_URLS = List.of(
            "/customer/register",
            "/customer/token",
            "/notices",
            "/contact",
            "/error",
            "/customer/login"
    );

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests((requests) -> {
                    PUBLIC_URLS.forEach(url -> requests.requestMatchers(url).permitAll());
                    requests.requestMatchers("/notices", "/contact", "/customer/register", "/error", "/customer/token").permitAll()
                            .requestMatchers("/myAccount")
                            .access(new WebExpressionAuthorizationManager(
                                    "hasRole('ADMIN') or hasAuthority('VIEWACCOUNT')"
                            ))
                            .requestMatchers("/myBalance")
                            .access(new WebExpressionAuthorizationManager(
                                    "hasRole('ADMIN') or hasAuthority('VIEWBALANCE')"
                            ))
                            .requestMatchers("/myCards")
                            .access(new WebExpressionAuthorizationManager(
                                    "hasRole('ADMIN') or hasAuthority('VIEWCARDS')"
                            ))
                            .requestMatchers("/myLoans")
                            .access(new WebExpressionAuthorizationManager(
                                    "hasRole('ADMIN') or hasAuthority('VIEWLOANS')"
                            ))
                            .requestMatchers(HttpMethod.GET, "/customer").authenticated()
                            .requestMatchers(HttpMethod.GET, "/customer/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })


                .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(configurer -> {
                    configurer.accessDeniedHandler(customAccessDeniedHandler);
                    configurer.authenticationEntryPoint(customBasicAuthenticationEntryPoint);
                });
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
