package com.qcard.domain.heart.repository;

import com.qcard.domain.account.entity.Account;
import com.qcard.domain.heart.entity.Heart;
import com.qcard.domain.question.entity.Answer;
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
