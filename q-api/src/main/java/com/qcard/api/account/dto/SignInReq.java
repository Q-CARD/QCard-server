package com.qcard.api.account.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class SignInReq {
    private String email;
    private String password;
}
