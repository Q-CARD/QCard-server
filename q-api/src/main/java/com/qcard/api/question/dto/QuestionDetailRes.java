package com.qcard.api.question.dto;

import com.qcard.api.answer.dto.AnswerRes;
import com.qcard.common.enums.AnswerType;
import com.qcard.common.enums.SortType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailRes {
    private Question question;

    private AnswerRes gpt;
    private List<AnswerRes> answers;


    public QuestionDetailRes(List<Answer> answers, Account account, List<Long> hearts, Map<Long, Integer> heartCnts, SortType sort) {
        this.question = answers.get(0).getQuestion();

        for (Answer answer : answers) {
            if (answer.getType() == AnswerType.TYPE_GPT) {
                this.gpt = new AnswerRes(answer);
                answers.remove(answer);
                break;
            }
        }

        if(sort == SortType.SORT_HEART) {
            this.answers = answers.stream()
                    .map(answer -> new AnswerRes(answer, account, hearts, heartCnts.get(answer.getId())))
                    .sorted(Comparator.comparingInt(AnswerRes::getHeartCount))
                    .collect(Collectors.toList());
        }
        else {
            this.answers = answers.stream()
                    .sorted((ans1, ans2) -> ans2.getModifiedAt().compareTo(ans1.getModifiedAt()))
                    .map(answer -> new AnswerRes(answer, account, hearts, heartCnts.get(answer.getId())))
                    .collect(Collectors.toList());
        }
    }

    public QuestionDetailRes(Question question, Account account) {
        this.question = question;
        this.answers = new ArrayList<>();
    }
}
