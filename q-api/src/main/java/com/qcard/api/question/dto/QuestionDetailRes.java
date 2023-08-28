package com.qcard.api.question.dto;

import com.qcard.api.answer.dto.AnswerRes;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailRes {
    private Long questionId;
    private String title;
    private List<AnswerRes> answers;


    public QuestionDetailRes(List<Answer> answers, Account account) {
        this.questionId = answers.get(0).getQuestion().getId();
        this.title = answers.get(0).getQuestion().getTitle();
        this.answers = answers.stream()
                .map(answer -> new AnswerRes(answer, account))
                .collect(Collectors.toList());
    }
}