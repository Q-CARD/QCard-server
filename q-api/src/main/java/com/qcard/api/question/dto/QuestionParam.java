package com.qcard.api.question.dto;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class QuestionParam {
    private QuestionType type;
    private Category category;
    private Boolean mine;
    private Integer size;
    private Integer page;

    public QuestionParam(QuestionType type, Category category, Boolean mine, Integer size, Integer page) {
        this.type = type == null ? QuestionType.TYPE_QCARD : type;
        this.category = category;
        this.mine = mine != null && mine;
        this.page = page == null ? 0 : page;
        this.size = size == null ? 10 : size;
    }

}
