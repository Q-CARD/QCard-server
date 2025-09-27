package com.qcard.common.dto;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import lombok.Getter;

@Getter
public class QuestionFilterReq {
    private QuestionType type;
    private Category category;
    private Boolean mine;

    public QuestionFilterReq(QuestionType type, Category category, Boolean mine) {
        this.type = type;
        this.category = category;
        this.mine = mine != null && mine;
    }
}
