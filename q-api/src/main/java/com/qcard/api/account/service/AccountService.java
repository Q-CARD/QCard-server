package com.qcard.api.account.service;

import com.qcard.api.account.dto.*;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.service.AccountDomainService;
import com.qcard.jwt.JwtService;
import com.qcard.jwt.TokenRes;
import com.qcard.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountDomainService accountDomainService;
    private final JwtService jwtService;
    private final RedisService redisService;

    public SignUpRes signUp(AccountReq accountReq) {
        if (!accountReq.isValid()) {
            throw new IllegalArgumentException("사용자에 대한 올바른 정보를 입력해주세요.");
        }
        else if (accountDomainService.existsAccountByEmail(accountReq.getEmail())) {
            throw new IllegalArgumentException("이미 계정이 존재합니다.");
        }

        Account account = accountDomainService.createAccount(
                accountReq.getEmail(),
                accountReq.getName(),
                jwtService.encryptPassword(accountReq.getPassword()));
        return new SignUpRes(account.getName());
    }

    public TokenRes signIn(SignInReq signInReq) {
        Account account = accountDomainService.findAccountByEmail(signInReq.getEmail());

        if (jwtService.isValidPassword(signInReq.getPassword(), account.getPassword())) {
            return jwtService.createJwt(account.getEmail(), account.getPassword());
        }
        else {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }
    }

    public TokenRes reissueToken(String refreshToken) {
        String email = redisService.getValues(refreshToken);
        if(email != null) {
            Account account = accountDomainService.findAccountByEmail(email);
            return jwtService.reissueJwt(account.getEmail(), account.getPassword(), refreshToken);
        }
        else {
            throw new IllegalArgumentException("잘못된 refresh token입니다.");
        }
    }

    public LogOutRes logout(String refreshToken) {
        String email = redisService.getValues(refreshToken);
        log.info("email: " + email);
        if(email != null){
            redisService.deleteValues(refreshToken);
            return new LogOutRes(email);
        }
        else{
            throw new IllegalArgumentException("잘못된 refresh token입니다.");
        }
    }
}
