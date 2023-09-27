package com.qcard.api.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountModifyReq {
    private String name;
    private String email;
    private String profile;
}
