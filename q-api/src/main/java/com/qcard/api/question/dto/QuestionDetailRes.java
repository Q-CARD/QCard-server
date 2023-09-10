package com.qcard.api.question.dto;

import com.qcard.api.answer.dto.AnswerRes;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.heart.entity.Heart;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailRes {
    private Question question;
    private List<AnswerRes> answers;


    public QuestionDetailRes(List<Answer> answers, Account account, List<Long> hearts, Map<Long, Integer> heartCnts) {
        this.question = answers.get(0).getQuestion();
        this.answers = answers.stream()
                .map(answer -> new AnswerRes(answer, account, hearts, heartCnts.get(answer.getId())))
                .collect(Collectors.toList());
    }

    public QuestionDetailRes(Question question, Account account) {
        this.question = question;
        this.answers = new ArrayList<>();
    }
}
