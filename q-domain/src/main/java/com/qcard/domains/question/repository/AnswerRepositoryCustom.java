package com.qcard.domains.question.repository;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnswerRepositoryCustom {
    Page<Answer> findAllAccount(Account account, Pageable pageable);

    List<Answer> findAllWithQuestionIdAndNotMyAccountOrderWithTypeDesc(Long questionId, Account account);
}
