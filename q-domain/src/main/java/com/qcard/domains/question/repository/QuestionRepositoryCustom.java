package com.qcard.domains.question.repository;

import com.qcard.common.dto.QuestionFilterReq;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionRepositoryCustom {
    Page<Question> findAllTypeCategoryAccount(QuestionFilterReq questionFilterReq, Account account, Pageable pageable);
}
