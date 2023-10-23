package com.qcard.domains.account.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 200)
    private String email;

    @Column
    private String password;

    @Column
    private String profile;

    @Column
    private Boolean isDeleted;

    @Builder
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void update(String name, String email, String profile) {
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public void updateIsDeleted() {
        this.isDeleted = true;
    }

}
