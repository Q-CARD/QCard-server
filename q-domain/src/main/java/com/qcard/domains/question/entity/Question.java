package com.qcard.domains.question.entity;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
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

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Column(length = 31)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 31)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Builder
    public Question(String title, Account account, Category category, QuestionType type) {
        this.title = title;
        this.account = account;
        this.category = category;
        this.type = type;
    }

}
