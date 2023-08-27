package com.qcard.api.question.dto;

import com.qcard.common.enums.Category;
import com.qcard.domains.question.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRes {
    private String title;
    private String category;

    public QuestionRes(Question entity) {
        this.title = entity.getTitle();
        this.category = entity.getCategory().name();
    }
}
