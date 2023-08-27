package com.qcard.api.account.service;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.service.AccountDomainService;
import com.qcard.api.account.dto.AccountReq;
import com.qcard.api.account.dto.AccountRes;
import com.qcard.jwt.JwtService;
import com.qcard.jwt.TokenRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDomainService accountDomainService;
    private final JwtService jwtService;

    public AccountRes signUp(AccountReq accountReq) {

        if (!accountReq.isValid()) {
            throw new IllegalArgumentException("사용자에 대한 올바른 정보를 입력해주세요.");
        }

        String name = accountReq.getEmail().split("@")[0];
        Account account = accountDomainService.createAccount(
                accountReq.getEmail(),
                name,
                jwtService.encryptPassword(accountReq.getPassword())
        );
        return new AccountRes(name);
    }

    public TokenRes signIn(AccountReq accountReq) {
        Account account = accountDomainService.findAccountByEmail(
                accountReq.getEmail()).orElseThrow(() -> new IllegalArgumentException("가입하지 않는 이메일입니다."));
        return jwtService.createJwt(
                account.getEmail(),
                account.getPassword()
        );
    }
}
