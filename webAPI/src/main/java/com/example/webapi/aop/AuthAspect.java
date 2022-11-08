package com.example.webapi.aop;

import com.example.webapi.config.AuthConfig;
import com.example.webapi.exception.AuthenticationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.mapstruct.ap.shaded.freemarker.template.utility.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {

    private final AuthConfig authConfig;

    @Autowired
    public AuthAspect(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    @Before("@annotation(AuthRequired)")
    public void authenticateBefore(JoinPoint joinPoint) {
        HttpServletRequest request = null;

        for (var arg : joinPoint.getArgs()) {
            if (arg instanceof HttpServletRequest req) {
                request = req;
                break;
            }
        }

        if (request == null) {
            throw new NullArgumentException("Method with annotation @AuthRequired must have HttpServletRequest in arguments");
        }

        String key = request.getHeader("Authorization");
        if (key == null) {
            throw new AuthenticationException("Request doesn't have authentication token");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", key);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(authConfig.getUrl() + "/authenticate", HttpMethod.POST, entity, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new AuthenticationException("Authentication is failed");
            }
        } catch (HttpClientErrorException e) {
            throw new AuthenticationException("Authentication is failed");
        }
    }
}
