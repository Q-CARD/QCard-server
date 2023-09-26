package com.qcard.config;

import com.qcard.filter.JwtExceptionFilter;
import com.qcard.jwt.JwtFilter;
import com.qcard.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtUtil jwtUtil;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtExceptionFilter jwtExceptionFilter = new JwtExceptionFilter();
        JwtFilter jwtFilter = new JwtFilter(jwtUtil);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtExceptionFilter, JwtFilter.class);
    }
}
