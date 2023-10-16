package com.qcard.api.account.dto;

import com.qcard.domains.account.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountDeleteRes {
    private String message;
    private Account account;
    public AccountDeleteRes(Account account) {
        this.account = account;
        this.message = "성공적으로 회원탈퇴를 완료했습니다.";
    }
}
