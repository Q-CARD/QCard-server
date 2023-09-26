package com.qcard.domains.question.service;

import com.qcard.common.enums.Category;
import com.qcard.common.enums.QuestionType;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Question;
import com.qcard.domains.question.repository.QuestionRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qcard.domains.question.entity.QQuestion.question;

@Service
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<Question> findAllTypeCategoryAccount(QuestionType type, Category category, Account account, Boolean isMine, Pageable pageable) {
        List<Question> content = getQuestions(type, category, account, isMine, pageable);
        JPAQuery<Long> countQuery = getCount(type, category, isMine, account);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private List<Question> getQuestions(QuestionType type, Category category, Account account, Boolean isMine, Pageable pageable) {
        return jpaQueryFactory.selectFrom(question)
                .where(typeEq(type), categoryEq(category), accountEq(isMine, account))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> getCount(QuestionType type, Category category, Boolean isMine, Account account) {
        return jpaQueryFactory
                .select(question.count())
                .from(question)
                .where(typeEq(type), categoryEq(category), accountEq(isMine, account));
    }

    private BooleanExpression typeEq(final QuestionType type) {
        return type != null ? question.type.eq(type) : null;
    }

    private BooleanExpression categoryEq(final Category category) {
        return category != null ? question.category.eq(category) : null;
    }

    private BooleanExpression accountEq(final Boolean isMine, final Account account) {
        return isMine ? question.account.eq(account) : null;
    }
}
