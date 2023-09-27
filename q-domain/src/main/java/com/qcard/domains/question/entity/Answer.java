package com.qcard.domains.question.entity;

import com.qcard.common.entity.BaseTimeEntity;
import com.qcard.common.enums.AnswerType;
import com.qcard.domains.account.entity.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "bigint unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private AnswerType type;

    @Builder
    public Answer(Question question, Account account, String content, AnswerType type) {
        this.question = question;
        this.account = account;
        this.content = content;
        this.type = type;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
