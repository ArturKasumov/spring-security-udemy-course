package com.arturk.config.filter;

import com.arturk.config.SecurityConfig;
import com.arturk.config.exception.CustomAuthenticationEntryPoint;
import com.arturk.exception.JwtAuthenticationException;
import com.arturk.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService customUserDetailsService;
    private final CustomAuthenticationEntryPoint entryPoint;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null) {
                throw new JwtAuthenticationException("Missing authorization header");
            }
            if (!authorizationHeader.startsWith("Bearer ")) {
                throw new JwtAuthenticationException("Missing Bearer token");
            }
            String token = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (username == null) {
                throw new JwtAuthenticationException("Missing username in jwt token");
            }

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception exception) {
            entryPoint.commence(request, response, new JwtAuthenticationException(exception.getMessage()));
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return SecurityConfig.PUBLIC_URLS.stream()
                .anyMatch(publicPath -> pathMatcher.match(publicPath, path));
    }

}
