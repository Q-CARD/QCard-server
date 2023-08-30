package com.qcard.api.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerCreateRes {
    private String content;

    public AnswerCreateRes(String content) {
        this.content = content;
    }
}