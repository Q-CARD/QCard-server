package com.qcard.domains.question.repository;

import com.qcard.api.question.dto.QuestionParam;
import com.qcard.domains.question.entity.Question;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<Question> findQuestionsByParam(QuestionParam questionParam);
}
