package com.qcard.api.heart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartCountRes {
    private Long answerId;
    private Integer heart_cnt;

    public HeartCountRes (Long answerId, Integer heart_cnt) {
        this.answerId = answerId;
        this.heart_cnt = heart_cnt;
    }
}
