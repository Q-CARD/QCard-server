package com.qcard.api.question.service;

import com.qcard.api.answer.dto.AnswerCreateRes;
import com.qcard.api.question.dto.QuestionMainRes;
import com.qcard.api.question.dto.QuestionReq;
import com.qcard.api.question.dto.QuestionRes;
import com.qcard.api.question.dto.QuestionZipRes;
import com.qcard.common.enums.Category;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.service.QuestionDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionDomainService questionDomainService;

    public List<QuestionRes> findQuestionByCategory(Category category) {
        List<Question> entities = questionDomainService.findQuestionByCategory(category);
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
                questionReq.getType()
        );

        return new QuestionRes(question);
    }
}
