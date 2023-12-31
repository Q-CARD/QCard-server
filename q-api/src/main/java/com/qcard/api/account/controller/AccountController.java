package com.qcard.api.account.controller;

import com.qcard.api.account.dto.*;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.service.AccountDomainService;
import com.qcard.api.account.service.AccountService;
import com.qcard.resolver.AuthAccount;
import com.qcard.jwt.TokenRes;
import jakarta.servlet.http.HttpServletRequest;
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
    private static final String ACCESS_HEADER = "Authorization";

    @PostMapping("/signup")
    public ResponseEntity<SignUpRes> signUp(@Valid @RequestBody AccountReq accountReq) {
        log.info("[SIGNUP-CONTROLLER] Request: " + accountReq);

        SignUpRes response = accountService.signUp(accountReq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenRes> signIn(@Valid @RequestBody SignInReq signInReq) {
        TokenRes response = accountService.signIn(signInReq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/reissue")
    public ResponseEntity<TokenRes> accessTokenReissue(HttpServletRequest request) {
        TokenRes response = accountService.reissueToken(request.getHeader(ACCESS_HEADER));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<LogOutRes> logOut(HttpServletRequest request) {
        LogOutRes response = accountService.logout(request.getHeader(ACCESS_HEADER));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<AccountDeleteRes> accountDelete(@AuthAccount Account account) {
        AccountDeleteRes response = accountService.deleteAccount(account);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<AccountRes> myAccountInfo(@AuthAccount Account account) {
        AccountRes response = new AccountRes(account);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<AccountRes> modifyAccountInfo(@AuthAccount Account account, @RequestBody AccountModifyReq request) {
        AccountRes response = accountService.modifyAccount(account, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
