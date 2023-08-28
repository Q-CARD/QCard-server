package com.qcard.api.question.controller;

import com.qcard.api.answer.dto.AnswerRes;
import com.qcard.api.answer.service.AnswerService;
import com.qcard.api.question.dto.QuestionRes;
import com.qcard.api.question.service.QuestionService;
import com.qcard.auth.AuthAccount;
import com.qcard.common.enums.Category;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping("/{category}")
    private ResponseEntity<List<QuestionRes>> QuestionByCategoryFind(@PathVariable Category category) {
        List<QuestionRes> res = questionService.findQuestionByCategory(category);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{questionId}")
    private ResponseEntity<List<AnswerRes>> questionDetail(@AuthAccount Account account, @PathVariable Long questionId) {
        List<AnswerRes> response = answerService.findAnswerByQuestionId(account, questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
