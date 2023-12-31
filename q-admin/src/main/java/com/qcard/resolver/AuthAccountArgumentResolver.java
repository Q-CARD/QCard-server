package com.qcard.resolver;

import com.qcard.domains.account.service.AccountDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthAccountArgumentResolver implements HandlerMethodArgumentResolver {

    private final AccountDomainService accountDomainService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthAccount.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthAccount authAccountAnnotation = parameter.getParameterAnnotation(AuthAccount.class);
        assert authAccountAnnotation != null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("[SIGNUP-AUTH] Authentication: " + authentication.getPrincipal());

        if (authentication.getPrincipal() == "anonymousUser") {
            return null;
        }

        return accountDomainService.findAccountByEmail(authentication.getName());
    }
}
