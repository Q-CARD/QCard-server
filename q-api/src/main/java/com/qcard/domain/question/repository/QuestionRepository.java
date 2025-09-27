package com.qcard.domain.question.repository;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domain.account.entity.Account;
import com.qcard.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
    List<Question> findAllByCategory(Category category);
}
