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

    @GetMapping("/{category}")
    private ResponseEntity<List<QuestionRes>> QuestionByCategoryFind(@PathVariable Category category) {
        List<QuestionRes> res = questionService.findQuestionByCategory(category);
        return ResponseEntity.ok(res);
    }
}
