package com.qcard.domains.answer.repository;

import com.qcard.domains.question.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionId(Long questionId);

    Optional<Answer> findAnswerById(Long answerId);
}