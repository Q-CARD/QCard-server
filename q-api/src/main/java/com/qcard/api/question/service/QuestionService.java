package com.qcard.api.question.service;

import com.qcard.api.question.dto.*;
import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.service.QuestionDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionDomainService questionDomainService;

    //TODO: QuestionRes로 변환작업 필요
    public Page<Question> findQuestionsByParam(Account account, QuestionParam questionParam, Pageable pageable) {
        Page<Question> entities = questionDomainService.findQuestionByParam(questionParam.getType(), questionParam.getCategory(), account, questionParam.getMine(), pageable);

        return entities;
    }

    public QuestionMainRes findQuestionOnMain() {
        QuestionZipRes questionZip = new QuestionZipRes(questionDomainService.findQuestionByCategory(Category.randomCategory()));
        QuestionRes questionRes = new QuestionRes(questionDomainService.findQuestionByRand());
        return new QuestionMainRes(questionZip, questionRes);
    }

    public QuestionRes createQuestion(Account account, QuestionReq questionReq) {
        Question question = questionDomainService.createQuestion(
                account,
                questionReq.getTitle(),
                questionReq.getCategory(),
                QuestionType.TYPE_CUSTOM
        );

        return new QuestionRes(question);
    }
}
