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
import org.springframework.data.util.Pair;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailRes {
    private QuestionRes question;
    private AnswerRes myAnswer;
    private AnswerRes gpt;
    private List<AnswerRes> answers;


    public QuestionDetailRes(Account account, List<Answer> answers, Answer myAnswer, Integer myAnswerCount, List<Long> hearts, Map<Long, Integer> heartCnts, SortType sort) {
        this.question = new QuestionRes(answers.get(0).getQuestion(), account);

        if(myAnswer != null) {
            this.myAnswer = new AnswerRes(myAnswer, myAnswerCount);
        }

        if (answers.get(0).getType() == AnswerType.TYPE_GPT) {
            this.gpt = new AnswerRes(answers.get(0));
            answers.remove(answers.get(0));
        }

        if(sort == SortType.SORT_HEART) {
            this.answers = answers.stream()
                    .map(answer -> new AnswerRes(answer, hearts, heartCnts.get(answer.getId())))
                    .sorted((ans1, ans2) -> ans2.getHeartCount().compareTo(ans1.getHeartCount()))
                    .collect(Collectors.toList());
        }
        else {
            this.answers = answers.stream()
                    .sorted((ans1, ans2) -> ans2.getModifiedAt().compareTo(ans1.getModifiedAt()))
                    .map(answer -> new AnswerRes(answer, hearts, heartCnts.get(answer.getId())))
                    .collect(Collectors.toList());
        }
    }

    public QuestionDetailRes(Question question, Account account) {
        this.question = new QuestionRes(question, account);
        this.answers = new ArrayList<>();
    }
}
