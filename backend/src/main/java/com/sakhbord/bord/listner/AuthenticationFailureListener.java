package com.sakhbord.bord.listner;

import com.sakhbord.bord.service.cache.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener {

    private final
    LoginAttemptService loginAttemptService;

    private final
    HttpServletRequest request;

    public AuthenticationFailureListener(LoginAttemptService loginAttemptService, HttpServletRequest request) {
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent ignoredEvent) {

        final String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader == null) {

            loginAttemptService.addCache(request.getRemoteAddr());

        } else {

            loginAttemptService.addCache(xfHeader.split(",")[0]);

        }

    }

}
