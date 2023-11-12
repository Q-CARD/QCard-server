package com.qcard.api.answer.service;

import com.qcard.api.answer.dto.AnswerCreateRes;
import com.qcard.api.answer.dto.AnswerMeRes;
import com.qcard.api.answer.dto.AnswerReq;
import com.qcard.api.answer.dto.AnswerUpdateReq;
import com.qcard.api.question.dto.QuestionDetailRes;
import com.qcard.common.enums.Category;
import com.qcard.common.enums.SortType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.heart.entity.Heart;
import com.qcard.domains.heart.service.HeartDomainService;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.service.AnswerDomainService;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.service.QuestionDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerDomainService answerDomainService;
    private final HeartDomainService heartDomainService;
    private final QuestionDomainService questionDomainService;

    public AnswerCreateRes createAnswer(Account account, AnswerReq answerReq) {
        if(answerDomainService.existsAnswerByQuestionIdAndAccount(account, answerReq.getQuestionId())) {
            throw new IllegalArgumentException("사용자의 답변이 이미 존재합니다.");
        }
        
        Answer answer = answerDomainService.createAnswer(
                answerReq.getQuestionId(),
                account,
                answerReq.getContent()
        );
        return new AnswerCreateRes(answer.getContent());
    }

    public QuestionDetailRes findAnswerByQuestionId(Account account, Long questionId, SortType sort) {
        List<Answer> entities = answerDomainService.findAnswerByQuestionId(questionId, account);
        Answer answer = answerDomainService.findAnswerByAccountAndQuestionId(account, questionId);
        Pair<Answer, Integer> myAnswer = Pair.of(answer, countMyAnswerHeart(answer));

        if(entities.isEmpty()) {
            Question question = questionDomainService.findQuestionById(questionId);
            return new QuestionDetailRes(question, account);
        }

        Map<Long, Integer> heartCounts = countHearts(entities);
        List<Heart> myHeartList = heartDomainService.findHeartByAccount(account);
        List<Long> heartedAnswerList = new ArrayList<>();
        if(!myHeartList.isEmpty()) {
            heartedAnswerList = heartDomainService.findHeartByAccount(account)
                    .stream().map(heart -> heart.getAnswer().getId()).toList();
        }

        return new QuestionDetailRes(entities, myAnswer, heartedAnswerList, heartCounts, sort);
    }

    public List<AnswerMeRes> getAnswersByAuth(Account account, Category category) {
        List<Answer> entities = answerDomainService.findAnswerByAccount(account, category);
        if(entities.isEmpty()) return new ArrayList<>();
        Map<Long, Integer> heartCounts = countHearts(entities);
        return entities.stream().map(entity -> new AnswerMeRes(heartCounts.get(entity.getId()), entity)).collect(Collectors.toList());
    }

    public AnswerMeRes updateAnswer(Account account, Long answerId, AnswerUpdateReq answerUpdateReq) throws AccessDeniedException {
        Answer answer = answerDomainService.findAnswerById(answerId);

        if(answer.getAccount() != account) {
            throw new AccessDeniedException("본인의 답변만 수정 가능 합니다.");
        }
        else{
            Answer newAnswer = answerDomainService.updateAnswer(answer, answerUpdateReq.getContent());
            return new AnswerMeRes(heartDomainService.countHeartByAnswerId(answer.getId()), newAnswer);
        }
    }

    public Integer countMyAnswerHeart(Answer answer) {
        return heartDomainService.countHeartByAnswerId(answer.getId());
    }

    public Map<Long, Integer> countHearts(List<Answer> entities) {
        List<Long> answerIds = entities.stream().map(Answer::getId).toList();
        return answerIds.stream().collect(Collectors.toMap(id -> id, heartDomainService::countHeartByAnswerId));
    }
}