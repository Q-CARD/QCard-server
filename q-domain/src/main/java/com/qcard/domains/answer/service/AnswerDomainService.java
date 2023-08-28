package com.qcard.domains.answer.service;

import com.qcard.common.enums.Type;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.answer.repository.AnswerRepository;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerDomainService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public Answer createAnswer(Long questionId, Account account, String content) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException(questionId + ": 존재하지 않는 질문입니다."));

        return answerRepository.save(
                Answer.builder()
                        .question(question)
                        .account(account)
                        .content(content)
                        .type(Type.TYPE_ANSWER)
                        .build()
        );
    }
}