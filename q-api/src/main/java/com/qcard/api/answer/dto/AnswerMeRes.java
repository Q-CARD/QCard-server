package com.qcard.api.answer.dto;

import com.qcard.api.account.dto.AccountRes;
import com.qcard.common.enums.Type;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
public class AnswerMeRes {
    private Question question;

    private Long answerId;
    private Type type;
    private AccountRes account;
    private String content;

    private Integer heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public AnswerMeRes(Integer hearCount, Answer answer) {
        this.question = answer.getQuestion();
        this.answerId = answer.getId();
        this.type = answer.getType();
        this.account = createdAccountRes(answer.getAccount());
        this.content = answer.getContent();
        this.heartCount = hearCount;
        this.createdAt = answer.getCreatedAt();
        this.modifiedAt = answer.getModifiedAt();
    }

    private AccountRes createdAccountRes(Account account) {
        return new AccountRes(account.getName(), account.getEmail());
    }
}
