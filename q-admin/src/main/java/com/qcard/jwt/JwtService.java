package com.qcard.jwt;

import com.qcard.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public TokenRes createJwt(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenRes tokenRes = jwtUtil.generateToken(authentication);
        redisService.setValues(tokenRes.getRefreshToken(), email, Duration.ofDays(14));

        return tokenRes;
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Boolean isValidPassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
