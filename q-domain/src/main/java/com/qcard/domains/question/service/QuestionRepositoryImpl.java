package com.qcard.domains.question.service;

import com.qcard.common.dto.QuestionFilterReq;
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
    public Page<Question> findAllTypeCategoryAccount(QuestionFilterReq questionFilterReq, Account account, Pageable pageable) {
        List<Question> content = getFilterQuestions(questionFilterReq, account, pageable);
        JPAQuery<Long> count = getFilterQuestionsCount(questionFilterReq, account);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public Page<Question> findAllAccount(Account account, Pageable pageable) {
        List<Question> content = getMyQuestions(account, pageable);
        JPAQuery<Long> count = getMyQuestionsCount(account);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }


    private List<Question> getFilterQuestions(QuestionFilterReq questionFilterReq, Account account, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(question)
                .where(typeEq(questionFilterReq.getType()),
                        categoryEq(questionFilterReq.getCategory()),
                        accountEq(questionFilterReq.getMine(), account)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private List<Question> getMyQuestions(Account account, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(question)
                .where(question.account.eq(account))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> getFilterQuestionsCount(QuestionFilterReq questionFilterReq, Account account) {
        return jpaQueryFactory
                .select(question.count())
                .from(question)
                .where(typeEq(questionFilterReq.getType()),
                        categoryEq(questionFilterReq.getCategory()),
                        accountEq(questionFilterReq.getMine(), account)
                );
    }

    private JPAQuery<Long> getMyQuestionsCount(Account account) {
        return jpaQueryFactory
                .select(question.count())
                .from(question)
                .where(question.account.eq(account));
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
