package com.qcard.api.answer.dto;

import com.qcard.api.account.dto.AccountRes;
import com.qcard.common.enums.AnswerType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class AnswerRes {
    private Long answerId;
    private AnswerType type;
    private AccountRes account;
    private String content;

    private Integer heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Boolean isHearted;

    public AnswerRes(Answer answer, List<Long> heartList, Integer heartCount) {
        this.answerId = answer.getId();
        this.type = answer.getType();
        this.content = answer.getContent();
        this.account = createdAccountRes(answer.getAccount());
        this.heartCount = heartCount;
        this.createdAt = answer.getCreatedAt();
        this.modifiedAt = answer.getModifiedAt();
        this.isHearted = Boolean.FALSE;

        if (heartList.contains(answer.getId())) {
            this.isHearted = Boolean.TRUE;
        }
    }

    public AnswerRes(Answer answer) {
        this.answerId = answer.getId();
        this.type = answer.getType();
        this.content = answer.getContent();
    }

    public AnswerRes(Answer answer, Integer heartCount) {
        this.answerId = answer.getId();
        this.type = answer.getType();
        this.content = answer.getContent();
        this.account = createdAccountRes(answer.getAccount());
        this.heartCount = heartCount;
        this.createdAt = answer.getCreatedAt();
        this.modifiedAt = answer.getModifiedAt();
    }

    private AccountRes createdAccountRes(Account account) {
        return new AccountRes(account);
    }
}
