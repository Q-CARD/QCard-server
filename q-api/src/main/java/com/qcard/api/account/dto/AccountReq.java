package com.qcard.api.account.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class AccountReq {
    private String email;
    private String password;

    public Boolean isValid() {
        return !Objects.equals(this.email, null)
                && !Objects.equals(this.password, null);
    }
}
