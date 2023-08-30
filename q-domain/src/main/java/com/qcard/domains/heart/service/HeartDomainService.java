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
        Boolean isHearted = existHeartByAccountAndAnswer(account, answer);

        if(isHearted) {
            throw new IllegalArgumentException(answerId + ": 이미 좋아요를 누른 답변입니다.");
        }
        else{
            return heartRepository.save(Heart.builder()
                    .account(account)
                    .answer(answer)
                    .build()
            );
        }
    }

    @Transactional(readOnly = true)
    public Heart findHeart(Account account, Long answerId) {
        Answer answer = answerDomainService.findAnswerById(answerId);
        return heartRepository.findHeartByAccountAndAnswer(account, answer);
    }

    public Integer countHeartByAnswerId(Long answerId) {
        return heartRepository.countHeartsByAnswerId(answerId);
    }

    public Boolean existHeartByAccountAndAnswer(Account account, Answer answer) {
        return heartRepository.existsHeartByAccountAndAnswer(account, answer);
    }

    public Integer deleteHeart(Account account, Long answerId) {
        Answer answer = answerDomainService.findAnswerById(answerId);

        return heartRepository.deleteHeartByAccountAndAnswer(account, answer);
    }

    @Transactional(readOnly = true)
    public List<Heart> findHeartByAccount(Account account) {
        return heartRepository.findByAccount(account);
    }
}
