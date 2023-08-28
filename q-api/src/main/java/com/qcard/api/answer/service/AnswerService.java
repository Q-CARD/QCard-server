package com.qcard.api.answer.service;

import com.qcard.api.answer.dto.AnswerCreateRes;
import com.qcard.api.answer.dto.AnswerReq;
import com.qcard.api.answer.dto.AnswerRes;
import com.qcard.api.heart.dto.HeartRes;
import com.qcard.api.question.dto.QuestionDetailRes;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.heart.entity.Heart;
import com.qcard.domains.heart.service.HeartDomainService;
import com.qcard.domains.question.service.AnswerDomainService;
import com.qcard.domains.question.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerDomainService answerDomainService;
    private final HeartDomainService heartDomainService;

    public AnswerCreateRes createAnswer(Account account, AnswerReq answerReq) {
        Answer answer = answerDomainService.createAnswer(
                answerReq.getQuestionId(),
                account,
                answerReq.getContent()
        );
        return new AnswerCreateRes(answer.getContent());
    }

    // TODO: heartDomainService.countHeartByAnswerId()로 하트수 기입
    // TODO: account == answer.getAccount()로 type 변경
    public QuestionDetailRes findAnswerByQuestionId(Account account, Long questionId) {
        List<Answer> entities = answerDomainService.findAnswerByQuestionId(questionId);
        return new QuestionDetailRes(entities, account);
    }
}