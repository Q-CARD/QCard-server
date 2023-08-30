package com.qcard.domains.heart.repository;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.heart.entity.Heart;
import com.qcard.domains.question.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Integer countHeartsByAnswerId(Long AnswerId);
    Heart findHeartByAccountAndAnswer(Account account, Answer answer);
    Integer deleteHeartByAccountAndAnswer(Account account, Answer answer);

    List<Heart> findByAccount(Account account);

    Boolean existsHeartByAccountAndAnswer(Account account, Answer answer);

}
