package com.qcard.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    CATEGORY_NW("네트워크"),
    CATEGORY_OS("운영체제"),
    CATEGORY_DB("데이터베이스"),
    CATEGORY_DATA_STRUCTURE("자료구조"),
    CATEGORY_JAVA("JAVA"),
    CATEGORY_PYTHON("Python"),
    CATEGORY_JS("JavaScript"),
    CATEGORY_DEVOPS_INFRA("DevOps/Infra"),
    CATEGORY_BACK("Backend"),
    CATEGORY_FRONT("Frontend"),
    CATEGORY_AI("AI"),
    CATEGORY_인성("인성 질문");


    private final String label;
}
