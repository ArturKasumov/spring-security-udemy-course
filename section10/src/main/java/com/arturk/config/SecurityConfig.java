package com.arturk.config;

import com.arturk.config.filter.EmailValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPoint customBasicAuthenticationEntryPoint;
    private final AccessDeniedHandler customAccessDeniedHandler;
    private final EmailValidationFilter emailValidationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/notices", "/contact", "/customer/register", "/error").permitAll()
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
                                .anyRequest().authenticated()
                )

                .addFilterBefore(emailValidationFilter, BasicAuthenticationFilter.class)

                .formLogin(AbstractHttpConfigurer::disable)

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
