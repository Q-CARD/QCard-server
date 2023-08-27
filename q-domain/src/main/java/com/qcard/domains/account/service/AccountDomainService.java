package com.qcard.domains.account.service;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDomainService {
    private final AccountRepository accountRepository;

    public Account createAccount(String email, String name, String password) {
        return accountRepository.save(
                Account.builder().email(email)
                        .name(name)
                        .password(password)
                        .build());
    }

    public Account findAccount(String email, String password) {
        return accountRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 존재하지 않습니다."));
    }

    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public String findAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다: id=" + accountId));
        return account.getName();
    }
}
