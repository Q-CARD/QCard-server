package com.qcard.api.question.dto;

import com.qcard.common.enums.Category;
import com.qcard.domains.question.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionMainRes {
    private QuestionZipRes questionZip;
    private QuestionRes questionDaily;

    public QuestionMainRes(QuestionZipRes questionZip, QuestionRes questionDaily) {
        this.questionZip = questionZip;
        this.questionDaily = questionDaily;
    }
}
