package com.qcard.api.account.controller;

import com.qcard.api.account.dto.SignInReq;
import com.qcard.api.account.dto.SignUpRes;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.service.AccountDomainService;
import com.qcard.api.account.dto.AccountReq;
import com.qcard.api.account.dto.AccountRes;
import com.qcard.api.account.service.AccountService;
import com.qcard.resolver.AuthAccount;
import com.qcard.jwt.TokenRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountDomainService accountDomainService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpRes> signUp(@Valid @RequestBody AccountReq accountReq) {
        SignUpRes response = accountService.signUp(accountReq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenRes> signIn(@Valid @RequestBody SignInReq signInReq) {
        TokenRes response = accountService.signIn(signInReq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<AccountRes> myAccountInfo(@AuthAccount Account account) {
        AccountRes response = new AccountRes(account.getName(), account.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
