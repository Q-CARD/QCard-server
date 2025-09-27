package com.qcard.api.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;

@Getter
public class AccountReq {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public Boolean isValid() {
        return !Objects.equals(this.email, null)
                && !Objects.equals(this.name, null)
                && !Objects.equals(this.password, null);
    }
}
