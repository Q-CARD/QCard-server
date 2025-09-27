package com.qcard.api.question.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionMainRes {
    private QuestionZipRes questionZip;
    private QuestionSimpleRes questionDaily;

    public QuestionMainRes(QuestionZipRes questionZip, QuestionSimpleRes questionDaily) {
        this.questionZip = questionZip;
        this.questionDaily = questionDaily;
    }
}
