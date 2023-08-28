package com.qcard.api.answer.dto;

import com.qcard.api.account.dto.AccountRes;
import com.qcard.common.enums.Type;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.StreamingHttpOutputMessage;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerRes {
    private Long answerId;
    private Type type;
    private AccountRes account;
    private String content;

    private Integer heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Boolean isHearted;

    private Boolean isMine;

    public AnswerRes(Answer answer, Account myAccount) {
        this.answerId = answer.getId();
        this.type = answer.getType();
        this.account = createdAccountRes(answer.getAccount());
        this.content = answer.getContent();
        // TODO: heart get api 만들고 연결하기
        this.heartCount = 1;
        this.createdAt = answer.getCreatedAt();
        this.modifiedAt = answer.getModifiedAt();
        this.isMine = Boolean.FALSE;

        if (myAccount.getId().equals(answer.getAccount().getId())) {
            this.isMine = Boolean.TRUE;
        }
    }

    private AccountRes createdAccountRes(Account account) {
        return new AccountRes(account.getName(), account.getEmail());
    }
}
