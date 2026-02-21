package com.arturk.config;

import com.arturk.model.entity.CustomerEntity;
import com.arturk.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/notices", "/contact").permitAll()
                        .anyRequest().authenticated()
        );
        http.formLogin(withDefaults());
        http.httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(CustomerService customerService) {
        return username -> {
            CustomerEntity customer = customerService.getCustomerByEmail(username);
            return new User(customer.getEmail(), customer.getPassword(), List.of(new SimpleGrantedAuthority(customer.getRole())));
        };
    }
}
