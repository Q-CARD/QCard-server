package com.qcard.domains.question.entity;

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

    @Builder
    public Question(String title, Category category) {
        this.title = title;
        this.category = category;
    }

}
