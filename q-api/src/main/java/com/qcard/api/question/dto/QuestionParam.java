package com.qcard.api.question.dto;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class QuestionParam {
    private QuestionType type;
    private Category category;
    private Boolean mine;

    public QuestionParam(QuestionType type, Category category, Boolean mine) {
        this.type = type == null ? QuestionType.TYPE_QCARD : type;
        this.category = category;
        this.mine = mine == null ? false : mine;
    }

}
