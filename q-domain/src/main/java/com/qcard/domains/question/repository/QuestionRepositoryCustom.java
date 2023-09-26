package com.qcard.domains.question.repository;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionRepositoryCustom {
    Page<Question> findQuestionsByParamPage(QuestionType type, Category category, Account account, Boolean isMine, Pageable pageable);
}