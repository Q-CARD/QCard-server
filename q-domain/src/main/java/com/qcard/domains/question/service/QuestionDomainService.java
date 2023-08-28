package com.qcard.domains.question.service;

import com.qcard.common.enums.Category;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionDomainService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public List<Question> findQuestionByCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }
}
