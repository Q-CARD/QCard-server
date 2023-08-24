package com.qcard.question.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    CAT_NW("CATEGORY_NETWORK", "네트워크"),
    CAT_OS("CATEGORY_OPERATING_SYSTEM", "운영체제"),
    CAT_DB("CATEGORY_DATABASE", "데이터베이스"),
    CAT_DATA_STRUCTURE("CATEGORY_DATA_STRUCTURE", "자료구조");

    private final String name;
    private final String type;
}
