package com.qcard.api.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRes {
    private String name;
    private String message;

    public SignUpRes(String name) {
        this.name = name;
        this.message = "성공적으로 회원가입 되셨습니다.";
    }
}
