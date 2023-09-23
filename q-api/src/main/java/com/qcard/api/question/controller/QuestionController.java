package com.qcard.api.question.controller;

import com.qcard.api.answer.service.AnswerService;
import com.qcard.api.question.dto.*;
import com.qcard.api.question.service.QuestionService;
import com.qcard.common.enums.QuestionType;
import com.qcard.resolver.AuthAccount;
import com.qcard.common.enums.Category;
import com.qcard.domains.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("")
    private ResponseEntity<QuestionRes> questionCreate(@AuthAccount Account account, @RequestBody QuestionReq questionReq) {
        QuestionRes response = questionService.createQuestion(account, questionReq);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{category}")
    private ResponseEntity<List<QuestionRes>> questionsByCategoryFind(@PathVariable Category category) {
        List<QuestionRes> res = questionService.findQuestionByCategory(category);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{questionId}")
    private ResponseEntity<QuestionDetailRes> questionDetail(@AuthAccount Account account, @PathVariable Long questionId) {
        QuestionDetailRes response = answerService.findAnswerByQuestionId(account, questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/main")
    private ResponseEntity<QuestionMainRes> questionsOnMain() {
        QuestionMainRes res = questionService.findQuestionOnMain();
        return ResponseEntity.ok(res);
    }
}
