package com.qcard.domains.question.repository;

import com.qcard.common.enums.Category;
import com.qcard.domains.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByCategory(Category category);
}
