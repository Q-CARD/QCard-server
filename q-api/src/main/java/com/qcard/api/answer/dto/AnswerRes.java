package com.qcard.api.answer.dto;

import com.qcard.api.account.dto.AccountRes;
import com.qcard.common.enums.Type;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerRes {
    private Long answerId;
    private Type type;
    private AccountRes account;
    private String content;
    private Integer heart_cnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public AnswerRes(Answer entity) {
        this.answerId = entity.getId();
        this.type = entity.getType();
        this.account = createdAccountRes(entity.getAccount());
        this.content = entity.getContent();
        // TODO: heart get api 만들고 연결하기
        this.heart_cnt = 1;
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }

    private AccountRes createdAccountRes(Account account) {
        return new AccountRes(account.getName(), account.getEmail());
    }
}
