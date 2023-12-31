package com.qcard.domains.account.service;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
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

    @Transactional(readOnly = true)
    public Optional<Account> findOpAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Account findAccountByEmail(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + ": 존재하지 않는 계정입니다"));
        if(account.getIsDeleted()) {
            throw new IllegalArgumentException(email + ": 탈퇴한 계정 이메일입니다.");
        }

        return account;
    }


    @Transactional(readOnly = true)
    public Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다"));
    }

    @Transactional(readOnly = true)
    public Boolean existsAccountByEmail(String email) {
        return accountRepository.existsAccountByEmail(email);
    }

    @Transactional
    public Account updateAccount(Account account, String name, String email, String profile) {
        account.update(name, email, profile);
        return findAccountById(account.getId());
    }

    public Account updateAccountIsDeleted(Account account) {
        account.updateIsDeleted();
        return findAccountById(account.getId());
    }
}
