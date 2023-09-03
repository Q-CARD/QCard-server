package com.qcard.domains.account.repository;

import com.qcard.domains.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsAccountByEmail(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);
}
