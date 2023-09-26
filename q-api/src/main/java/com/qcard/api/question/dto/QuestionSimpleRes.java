package com.qcard.api.question.dto;

import com.qcard.common.enums.QuestionType;
import com.qcard.domains.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionSimpleRes {

    private Long questionId;
    private String title;
    private String category;

    private QuestionType type;

    public QuestionSimpleRes(Question entity) {
        this.questionId = entity.getId();
        this.title = entity.getTitle();
        this.category = entity.getCategory().name();
        this.type = entity.getType();
    }
}
