package com.qcard.api.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRes {
    private String name;
    private String email;

    public AccountRes(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
