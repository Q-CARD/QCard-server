package com.qcard.api.question.service;

import com.qcard.api.question.dto.*;
import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.service.QuestionDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionDomainService questionDomainService;

    public List<QuestionRes> findQuestionsByParam(Account account, QuestionParam questionParam) {
        List<Question> entities;
        Boolean isMine = questionParam.getMine();
        log.info("Boolean: " + isMine);

        if(isMine) {
            entities = questionDomainService.findQuestionByCategoryAndTypeAndAccount(
                    questionParam.getCategory(), questionParam.getType(), account);
        }
        else {
            entities = questionDomainService.findQuestionByCategoryAndType(
                    questionParam.getCategory(), questionParam.getType());
        }
        return entities.stream().map(QuestionRes::new).collect(Collectors.toList());
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
