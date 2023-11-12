package com.qcard.config;

import com.qcard.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headers) ->
                        headers.frameOptions().sameOrigin()
                )
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers(antMatcher("/h2-dev/**")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.OPTIONS, "/**/*")).permitAll()
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .requestMatchers(antMatcher("/questions/**")).permitAll()
                                .requestMatchers(antMatcher("/accounts/signup"), antMatcher("/accounts/signin")).permitAll()
                                .anyRequest().permitAll()
                )
                .addFilter(corsConfig.securityCorsFilter())
                .apply(new JwtSecurityConfig(jwtUtil));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
