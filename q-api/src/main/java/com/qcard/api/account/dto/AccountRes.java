package com.qcard.api.account.dto;

import com.qcard.domains.account.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRes {
    private String name;
    private String email;
    private String profile;

    public AccountRes(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
        this.profile = account.getProfile();
    }
}
