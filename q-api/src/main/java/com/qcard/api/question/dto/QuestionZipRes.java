package com.qcard.api.question.dto;

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
public class QuestionZipRes {
    private String category;
    private List<QuestionRes> questions;

    public QuestionZipRes(List<Question> entities) {
        this.category = entities.get(0).getCategory().toString();
        this.questions = entities.subList(0, 5)
                .stream().map(QuestionRes::new).collect(Collectors.toList());
    }
}
