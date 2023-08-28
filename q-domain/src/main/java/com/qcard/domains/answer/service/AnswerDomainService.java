package com.qcard.domains.answer.service;

import com.qcard.common.enums.Type;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.answer.repository.AnswerRepository;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.repository.QuestionRepository;
import com.qcard.domains.question.service.QuestionDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerDomainService {
    private final AnswerRepository answerRepository;
    private final QuestionDomainService questionDomainService;

    public Answer createAnswer(Long questionId, Account account, String content) {
        Question question = questionDomainService.findQuestionById(questionId);

        return answerRepository.save(
                Answer.builder()
                        .question(question)
                        .account(account)
                        .content(content)
                        .type(Type.TYPE_ANSWER)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswerByQuestionId(Long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public Answer findAnswerById(Long answerId) {
        return answerRepository.findAnswerById(answerId).orElseThrow(
                () -> new IllegalArgumentException(answerId + ": 존재하지 않는 답변입니다.")
        );
    }
}