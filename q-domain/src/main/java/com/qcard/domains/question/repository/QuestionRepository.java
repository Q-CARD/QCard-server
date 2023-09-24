package com.qcard.domains.question.repository;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByCategory(Category category);

    List<Question> findQuestionsByCategoryAndType(Category category, QuestionType type);

    List<Question> findQuestionsByCategoryAndTypeAndAccount(Category category, QuestionType type, Account account);
}
