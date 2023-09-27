package com.qcard.domains.question.service;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.entity.Answer;
import com.qcard.domains.question.repository.AnswerRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qcard.domains.question.entity.QAnswer.answer;
import static com.qcard.domains.question.entity.QQuestion.question;

@Service
@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<Answer> findAllAccount(Account account, Pageable pageable) {
        List<Answer> content = getMyAnswers(account, pageable);
        JPAQuery<Long> count = getMyAnswersCount(account);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    private List<Answer> getMyAnswers(Account account, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(answer)
                .where(answer.account.eq(account))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> getMyAnswersCount(Account account) {
        return jpaQueryFactory
                .select(answer.count())
                .from(answer)
                .where(answer.account.eq(account));
    }
}
