package com.qcard.domains.question.service;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.AnswerType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.repository.AccountRepository;
import com.qcard.domains.question.repository.AnswerRepository;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerDomainService {
    private final AnswerRepository answerRepository;
    private final QuestionDomainService questionDomainService;
    private final AccountRepository accountRepository;

    public Answer createAnswer(Long questionId, Account account, String content) {
        Question question = questionDomainService.findQuestionById(questionId);

        return answerRepository.save(
                Answer.builder()
                        .question(question)
                        .account(account)
                        .content(content)
                        .type(AnswerType.TYPE_ANSWER)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswerByQuestionId(Long questionId, Account account) {
        return answerRepository.findAllWithQuestionIdAndNotMyAccountOrderWithTypeDesc(questionId, account);
    }

    public Answer findAnswerByAccountAndQuestionId(Account account, Long questionId) {
        return answerRepository.findAnswerByAccountAndQuestionId(account, questionId);
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswerByAccount(Account account, Category category) {
        if(category == null) {
            return answerRepository.findAllByAccount(account);
        }
        else{
            return answerRepository.findAllByAccountAndQuestion_Category(account, category);
        }
    }

    @Transactional(readOnly = true)
    public Answer findAnswerById(Long answerId) {
        return answerRepository.findAnswerById(answerId).orElseThrow(
                () -> new IllegalArgumentException(answerId + ": 존재하지 않는 답변입니다.")
        );
    }

    public Answer updateAnswer(Answer answer, String content) {
        answer.updateContent(content);
        return answerRepository.save(answer);
    }

    public Boolean existsAnswerByQuestionIdAndAccount(Account account, Long questionId) {
        return answerRepository.existsByAccountAndQuestion_Id(account, questionId);
    }
}