package com.qcard.api.heart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartRes {
    private Long answerId;

    @Builder
    public HeartRes(Long answerId) {
        this.answerId = answerId;
    }
}
