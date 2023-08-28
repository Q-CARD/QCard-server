package com.qcard.api.account.service;

import com.qcard.api.account.dto.SignInReq;
import com.qcard.api.account.dto.SignUpRes;
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

    public SignUpRes signUp(AccountReq accountReq) {

        if (!accountReq.isValid()) {
            throw new IllegalArgumentException("사용자에 대한 올바른 정보를 입력해주세요.");
        }

        Account account = accountDomainService.createAccount(
                accountReq.getEmail(),
                accountReq.getName(),
                jwtService.encryptPassword(accountReq.getPassword())
        );
        return new SignUpRes(account.getName());
    }

    public TokenRes signIn(SignInReq signInReq) {
        Account account = accountDomainService.findAccountByEmail(
                signInReq.getEmail());
        return jwtService.createJwt(
                account.getEmail(),
                account.getPassword()
        );
    }
}
