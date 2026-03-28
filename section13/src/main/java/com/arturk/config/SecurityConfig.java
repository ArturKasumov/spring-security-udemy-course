package com.arturk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeycloakRoleConverter keycloakRoleConverter;

    public static final List<String> PUBLIC_URLS = List.of(
            "/notices",
            "/contact",
            "/error"
    );

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests((requests) -> {
                    PUBLIC_URLS.forEach(url -> requests.requestMatchers(url).permitAll());
                    requests.requestMatchers("/myAccount")
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
                            .anyRequest().authenticated();
                })
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(jwtAuthenticationConverter))
                );

        return http.build();
    }
}
