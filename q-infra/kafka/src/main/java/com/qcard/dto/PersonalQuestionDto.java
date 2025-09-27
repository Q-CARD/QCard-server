package com.qcard.dto;

import com.qcard.common.enums.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalQuestionDto {
    private Long questionId;
    private String title;
    private Category category;

    @Builder
    public PersonalQuestionDto(Long questionId, String title, Category category) {
        this.questionId = questionId;
        this.title = title;
        this.category = category;
    }
}
