package com.qcard.domains.heart.service;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.question.service.AnswerDomainService;
import com.qcard.domains.heart.entity.Heart;
import com.qcard.domains.heart.repository.HeartRepository;
import com.qcard.domains.question.entity.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartDomainService {
    private final HeartRepository heartRepository;
    private final AnswerDomainService answerDomainService;

    public Heart createHeart(Account account, Long answerId) {
        Answer answer = answerDomainService.findAnswerById(answerId);

        return heartRepository.save(Heart.builder()
                .account(account)
                .answer(answer)
                .build()
        );
    }

    @Transactional(readOnly = true)
    public Heart findHeart(Account account, Long answerId) {
        Answer answer = answerDomainService.findAnswerById(answerId);
        return heartRepository.findHeartByAccountAndAnswer(account, answer);
    }

    public Integer countHeartByAnswerId(Long answerId) {
        return heartRepository.countHeartsByAnswerId(answerId);
    }

    public Heart deleteHeart(Account account, Long answerId) {
        Answer answer = answerDomainService.findAnswerById(answerId);

        return heartRepository.deleteHeartByAccountAndAnswer(account, answer)
                .orElseThrow(() -> new IllegalArgumentException(answerId + ": 하트를 누른 기록이 없기에 삭제할 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Heart> findHeartByAccount(Account account) {
        return heartRepository.findByAccount(account);
    }
}
