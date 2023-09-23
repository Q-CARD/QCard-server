package com.qcard.api.question.dto;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import lombok.Getter;

@Getter
public class QuestionReq {
    private String title;
    private Category category;
    private QuestionType type;
}
