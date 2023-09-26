package com.qcard.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcard.common.exception.ErrorResponse;
import com.qcard.common.exception.ErrorStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e){
            throwException(response, "JWT EXPIRED", e);
        } catch (JwtException | ServletException e){
            throwException(response, "JWT INVALID", e);
        }
    }

    private void throwException(
            HttpServletResponse response,
            String message,
            Exception e
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(ErrorStatus.INTERNAL_SERVER_ERROR.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(
                    ErrorResponse
                            .builder()
                            .error(ErrorStatus.INTERNAL_SERVER_ERROR.getCode())
                            .details(e.getMessage())
                            .code(ErrorStatus.INTERNAL_SERVER_ERROR.getCode())
                            .message(message)
                            .build()
            ));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
