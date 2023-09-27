package com.qcard.api.question.dto;

import com.qcard.api.account.dto.AccountRes;
import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRes {
    private Long questionId;
    private String title;
    private Category category;

    private QuestionType type;
    private AccountRes account;
    private Boolean isMine;

    public QuestionRes(Question entity, Account account) {
        this.questionId = entity.getId();
        this.title = entity.getTitle();
        this.category = entity.getCategory();
        this.type = entity.getType();
        this.account = createAccountRes(entity.getAccount());
        this.isMine = isMine(entity.getAccount(), account);
    }

    private AccountRes createAccountRes(Account accountEntity) {
        if(accountEntity == null) {
            return null;
        }
        else {
            return new AccountRes(accountEntity);
        }
    }

    private Boolean isMine(Account accountEntity, Account accountMe) {
        if(accountEntity == null) {
            return false;
        }
        else{
            return accountEntity.equals(accountMe);
        }
    }
}
