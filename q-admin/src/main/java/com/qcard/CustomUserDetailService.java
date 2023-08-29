package com.qcard;

import com.qcard.domains.account.entity.Account;
import com.qcard.domains.account.service.AccountDomainService;
import com.qcard.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AccountDomainService accountDomainService;
    private final JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountDomainService.findOpAccountByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email + ": 존재하지 않는 계정입니다."));
    }

    public UserDetails createUserDetails(Account account) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        return new User(
                account.getEmail(),
                jwtService.encryptPassword(account.getPassword()),
                Collections.singleton(grantedAuthority)
        );
    }
}
