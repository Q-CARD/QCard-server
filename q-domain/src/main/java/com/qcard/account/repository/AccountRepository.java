package com.qcard.account.repository;

import com.qcard.account.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Member, Long> {
}
