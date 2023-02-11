package com.sakhbord.bord.listner;

import com.sakhbord.bord.service.cache.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {

    private final
    LoginAttemptService loginAttemptService;

    private final
    HttpServletRequest request;

    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService, HttpServletRequest request) {
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }


    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent ignoredEvent) {

        final String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader == null) {

            loginAttemptService.deleteCache(request.getRemoteAddr());

        } else {

            loginAttemptService.deleteCache(xfHeader.split(",")[0]);

        }

    }

}
