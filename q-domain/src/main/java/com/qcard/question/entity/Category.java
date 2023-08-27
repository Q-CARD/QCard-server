package com.qcard.question.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    CAT_NW("네트워크"),
    CAT_OS("운영체제"),
    CAT_DB("데이터베이스"),
    CAT_DATA_STRUCTURE("자료구조"),
    CAT_JAVA("JAVA"),
    CAT_PYTHON("Python"),
    CAT_JS("JavaScript"),
    CAT_DEVOPS_INFRA("DevOps/Infra"),
    CAT_BACK("Backend"),
    CAT_FRONT("Frontend"),
    CAT_AI("AI"),
    CAT_인성("인성 질문");


    private final String label;
}
