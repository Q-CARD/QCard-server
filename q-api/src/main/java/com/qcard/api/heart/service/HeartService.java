package com.qcard.api.heart.service;

import com.qcard.api.heart.dto.HeartRes;
import com.qcard.domains.account.entity.Account;
import com.qcard.domains.heart.entity.Heart;
import com.qcard.domains.heart.service.HeartDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartDomainService heartDomainService;

    public HeartRes createHeart(Account account, Long answerId) {
        Heart heart = heartDomainService.createHeart(account, answerId);
        return new HeartRes(answerId);
    }

    public HeartRes deleteHeart(Account account, Long answerId) {
        Heart heart = heartDomainService.deleteHeart(account, answerId);
        return new HeartRes(answerId);
    }
}
