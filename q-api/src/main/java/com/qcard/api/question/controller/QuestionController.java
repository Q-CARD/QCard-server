package com.qcard.api.question.controller;

import com.qcard.api.question.dto.QuestionRes;
import com.qcard.api.question.service.QuestionService;
import com.qcard.common.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/categories/{category}")
    private ResponseEntity<List<QuestionRes>> questionByCategoryFind(@PathVariable Category category) {
        List<QuestionRes> res = questionService.findQuestionByCategory(category);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    private ResponseEntity<QuestionRes> questionByIdFind(@PathVariable Long id) {
        QuestionRes res = questionService.findQuestion(id);
        return ResponseEntity.ok(res);
    }
}
