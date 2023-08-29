package com.qcard.api.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerCreateRes {
    private String content;
    private String message;

    public AnswerCreateRes(String content) {
        this.content = content;
        this.message = "성공적으로 답변을 등록했습니다.";
    }
}