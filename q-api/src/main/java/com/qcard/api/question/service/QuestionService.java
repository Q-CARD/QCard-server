package com.qcard.api.question.service;

import com.qcard.api.question.dto.QuestionRes;
import com.qcard.common.enums.Category;
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

    public QuestionRes findQuestion(Long id) {
        return new QuestionRes(questionDomainService.findQuestionByPk(id));
    }
}
