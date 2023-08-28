package com.qcard.api.account.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class AccountReq {
    private String name;
    private String email;
    private String password;

    public Boolean isValid() {
        return !Objects.equals(this.email, null)
                && !Objects.equals(this.name, null)
                && !Objects.equals(this.password, null);
    }
}
