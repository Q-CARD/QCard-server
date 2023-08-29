package com.qcard.api.answer.controller;

import com.qcard.api.answer.dto.AnswerCreateRes;
import com.qcard.api.answer.dto.AnswerMeRes;
import com.qcard.api.answer.dto.AnswerReq;
import com.qcard.api.answer.dto.AnswerRes;
import com.qcard.api.answer.service.AnswerService;
import com.qcard.api.heart.dto.HeartReq;
import com.qcard.api.heart.dto.HeartRes;
import com.qcard.auth.AuthAccount;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<AnswerCreateRes> answerCreate(@AuthAccount Account account, @RequestBody AnswerReq answerReq) {
        AnswerCreateRes response = answerService.createAnswer(account, answerReq);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AnswerMeRes>> answersByAuth(@AuthAccount Account account) {
        List<AnswerMeRes> response = answerService.getAnswersByAuth(account);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}