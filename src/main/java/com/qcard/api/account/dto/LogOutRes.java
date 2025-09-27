package com.qcard.api.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogOutRes {
    private String email;
    private String message;

    public LogOutRes(String email) {
        this.email = email;
        this.message = "성공적으로 로그아웃했습니다.";
    }
}
