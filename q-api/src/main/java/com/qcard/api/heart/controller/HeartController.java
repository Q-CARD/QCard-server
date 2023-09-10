package com.qcard.api.heart.controller;

import com.qcard.api.heart.dto.HeartRes;
import com.qcard.api.heart.service.HeartService;
import com.qcard.resolver.AuthAccount;
import com.qcard.domains.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hearts")
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/{answerId}")
    public ResponseEntity<HeartRes> heartCreate(@AuthAccount Account account, @PathVariable Long answerId) {
        HeartRes res = heartService.createHeart(account, answerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<HeartRes> heartDelete(@AuthAccount Account account, @PathVariable Long answerId) {
        HeartRes res = heartService.deleteHeart(account, answerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

