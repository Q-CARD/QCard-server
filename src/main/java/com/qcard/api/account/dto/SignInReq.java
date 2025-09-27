package com.qcard.api.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Getter
public class SignInReq {
    @NotNull
    private String email;

    @NotNull
    private String password;

}
