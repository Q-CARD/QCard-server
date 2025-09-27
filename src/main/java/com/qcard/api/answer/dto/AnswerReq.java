package com.qcard.api.answer.dto;

import lombok.Getter;

@Getter
public class AnswerReq {
    private Long questionId;
    private String content;
}
