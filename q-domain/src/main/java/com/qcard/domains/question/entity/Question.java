package com.qcard.domains.question.entity;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 31)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 31)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Builder
    public Question(String title, Category category, QuestionType type) {
        this.title = title;
        this.category = category;
        this.type = type;
    }

}
