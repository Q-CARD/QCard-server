package com.qcard.domains.question.service;

import com.qcard.common.dto.QuestionFilterReq;
import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.qcard.domains.account.entity.QAccount.account;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionDomainService {
    private final QuestionRepository questionRepository;
    Random randomIdGenerator = new Random();

    @Transactional(readOnly = true)
    public List<Question> findQuestionByCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }

    @Transactional(readOnly = true)
    public Page<Question> findQuestionByParam(QuestionFilterReq questionFilterReq, Account account, Pageable pageable) {
        return questionRepository.findAllTypeCategoryAccount(questionFilterReq, account, pageable);
    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException(questionId + ": 존재하지 않는 질문입니다."));
    }

    @Transactional(readOnly = true)
    public Question findQuestionByPk(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));
    }

    public Question findQuestionByRand() {
        Long count = questionRepository.count();
        Long randId = ThreadLocalRandom.current().nextLong(count);
        return questionRepository.findById(randId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));
    }

    public Question createQuestion(Account account, String title, Category category, QuestionType type) {
        return questionRepository.save(Question.builder()
                .title(title)
                .account(account)
                .category(category)
                .type(type)
                .build()
        );
    }
}
