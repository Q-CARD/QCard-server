package com.qcard.domains.question.repository;

import com.qcard.common.enums.Category;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerRepositoryCustom {
    List<Answer> findAllByQuestionIdOrderByTypeDesc(Long questionId);

    List<Answer> findAllByAccount(Account account);

    Optional<Answer> findAnswerById(Long answerId);

    List<Answer> findAllByAccountAndQuestion_Category(Account account, Category category);
}