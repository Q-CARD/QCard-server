package com.qcard.api.answer.dto;

import com.qcard.api.account.dto.AccountRes;
import com.qcard.common.enums.Type;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerMeRes {
    private Long answerId;
    private Type type;
    private AccountRes account;
    private String content;

    private Integer heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public AnswerMeRes(Answer answer) {
        this.answerId = answer.getId();
        this.type = answer.getType();
        this.account = createdAccountRes(answer.getAccount());
        this.content = answer.getContent();
        this.heartCount = 1;
        this.createdAt = answer.getCreatedAt();
        this.modifiedAt = answer.getModifiedAt();
    }

    private AccountRes createdAccountRes(Account account) {
        return new AccountRes(account.getName(), account.getEmail());
    }
}
