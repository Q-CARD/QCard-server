package com.qcard.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AnswerType {
    TYPE_ANSWER("사용자 작성 답변"),
    TYPE_GPT("GPT 모범 답안");

    private final String label;
}
