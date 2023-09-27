package com.qcard.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuestionType {

    TYPE_QCARD("QCARD 제공 질문"),
    TYPE_CUSTOM("유저 등록 질문");

    private final String label;
}
