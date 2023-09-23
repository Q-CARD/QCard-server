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
    private AnswerType answerType;
    private AccountRes account;
    private String content;

    private Integer heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Boolean isHearted;

    private Boolean isMine;

    public AnswerRes(Answer answer, Account myAccount, List<Long> heartList, Integer heartCount) {
        this.answerId = answer.getId();
        this.answerType = answer.getAnswerType();
        this.account = createdAccountRes(answer.getAccount());
        this.content = answer.getContent();
        this.heartCount = heartCount;
        this.createdAt = answer.getCreatedAt();
        this.modifiedAt = answer.getModifiedAt();
        this.isMine = Boolean.FALSE;
        this.isHearted = Boolean.FALSE;

        if (myAccount.getId().equals(answer.getAccount().getId())) {
            this.isMine = Boolean.TRUE;
        }

        if (heartList.contains(answer.getId())) {
            this.isHearted = Boolean.TRUE;
        }
    }

    public AnswerRes(Answer answer) {
        this.answerId = answer.getId();
        this.answerType = answer.getAnswerType();
        this.content = answer.getContent();
    }

    private AccountRes createdAccountRes(Account account) {
        return new AccountRes(account.getName(), account.getEmail());
    }
}
