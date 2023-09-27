package com.qcard.api.question.controller;

import com.qcard.api.answer.service.AnswerService;
import com.qcard.api.question.dto.*;
import com.qcard.api.question.service.QuestionService;
import com.qcard.common.dto.QuestionFilterReq;
import com.qcard.common.enums.SortType;
import com.qcard.domains.question.entity.Question;
import com.qcard.resolver.AuthAccount;
import com.qcard.domains.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("")
    private ResponseEntity<QuestionSimpleRes> questionCreate(@AuthAccount Account account, @RequestBody QuestionReq questionReq) {
        QuestionSimpleRes response = questionService.createQuestion(account, questionReq);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO: questionRes로 변환작업 필요
    @GetMapping("")
    private ResponseEntity<Page<QuestionRes>> questionList(@AuthAccount Account account, QuestionFilterReq questionFilterReq, Pageable pageable) {
        Page<QuestionRes> res = questionService.findQuestionsByParam(account, questionFilterReq, pageable);
        return ResponseEntity.ok(res);
    }
    
    @GetMapping("/{questionId}")
    private ResponseEntity<QuestionDetailRes> questionDetail(@AuthAccount Account account, @PathVariable Long questionId, @RequestParam SortType sort) {
        QuestionDetailRes response = answerService.findAnswerByQuestionId(account, questionId, sort);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/main")
    private ResponseEntity<QuestionMainRes> questionsOnMain() {
        QuestionMainRes res = questionService.findQuestionOnMain();
        return ResponseEntity.ok(res);
    }
}
