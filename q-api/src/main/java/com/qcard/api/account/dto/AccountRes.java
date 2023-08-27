package com.qcard.api.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRes {
    private String name;
    private String message;

    public AccountRes(String name) {
        this.name = name;
        this.message = "성공적으로 회원가입 되셨습니다.";
    }
}
