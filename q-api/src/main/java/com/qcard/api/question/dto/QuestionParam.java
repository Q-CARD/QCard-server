package com.qcard.api.question.dto;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class QuestionParam {
    private QuestionType type;
    private Category category;
    private Boolean mine;
    private Integer size;
    private Integer page;

}
