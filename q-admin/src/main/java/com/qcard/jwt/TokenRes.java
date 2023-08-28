package com.qcard.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenRes {
    private String accessToken;
    private String refreshToken;

    @Builder
    public TokenRes(String accessToken, String refreshToken) {
        this.accessToken = "Bearer " + accessToken;
        this.refreshToken = refreshToken;
    }
}
