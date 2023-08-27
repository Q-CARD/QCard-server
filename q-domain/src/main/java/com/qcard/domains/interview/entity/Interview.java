package com.qcard.domains.interview.entity;

import com.qcard.common.enums.Category;
import com.qcard.domains.account.entity.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interview {

    @Id
    @Column(columnDefinition = "bigint unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Column (name = "category_1", length = 31)
    private Category category1;

    @Column (name = "category_2", length = 31)
    private Category category2;

    @Column (name = "category_3", length = 31)
    private Category category3;

    @Builder
    public Interview(Account account, Category category1, Category category2, Category category3) {
        this.account = account;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }
}
