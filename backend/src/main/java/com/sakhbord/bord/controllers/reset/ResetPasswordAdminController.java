package com.sakhbord.bord.controllers.reset;

import com.sakhbord.bord.email.SendEmail;
import com.sakhbord.bord.models.admin.Admin;
import com.sakhbord.bord.models.payload.request.admin.ResetAdminRequest;
import com.sakhbord.bord.models.payload.response.MessageResponse;
import com.sakhbord.bord.repository.service.ServiceJpa;
import com.sakhbord.bord.service.cache.LoginAttemptService;
import com.sakhbord.bord.validation.ValidationRegExp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.sakhbord.bord.config.Constants.STATIC_NO;
import static com.sakhbord.bord.config.Constants.STATIC_OK;

@RestController
@RequestMapping("/api")
public class ResetPasswordAdminController {

    @Value("${sakhshop.app.main.site.domain.admin}")
    String siteDomain;

    public final
    SendEmail sendEmail;

    public final
    ValidationRegExp validationRegExp;

    public final
    LoginAttemptService limitLogin;

    public final
    PasswordEncoder encoder;

    private final
    ServiceJpa serviceJpa;

    public ResetPasswordAdminController(SendEmail sendEmail, ValidationRegExp validationRegExp, LoginAttemptService limitLogin, PasswordEncoder encoder, ServiceJpa serviceJpa) {
        this.sendEmail = sendEmail;
        this.validationRegExp = validationRegExp;
        this.limitLogin = limitLogin;
        this.encoder = encoder;
        this.serviceJpa = serviceJpa;
    }


    @PostMapping(value = "/resetPasswordAdmin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetAdminRequest resetAdminRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetAdminRequest.email() != null
                && !resetAdminRequest.email().equals("")
                && resetAdminRequest.email().length() >= 8
                && resetAdminRequest.email().length() <= 58
                && validationRegExp.emailValidationRegExp(resetAdminRequest.email())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && Boolean.TRUE.equals(serviceJpa.existsAdminByEmail(resetAdminRequest.email()))) {

            // Создаём token для ссылки
            String token = UUID.randomUUID().toString();

            serviceJpa.updateAdminTokenByEmail(token, resetAdminRequest.email());

            String urlResetPasswordAdmin = siteDomain + "/resetPassword/newPassword/" + token;
            sendEmail.resetPasswordAdmin(request.getServerName(), urlResetPasswordAdmin);

        }
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }


    @PostMapping(value = "/checkTokenAdminResetPassword", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkServerToken(@RequestBody ResetAdminRequest resetAdminRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetAdminRequest.token() == null
                || resetAdminRequest.token().equals("")
                || resetAdminRequest.token().length() != 36
                || limitLogin.isBlocked(request.getRemoteAddr())
                || !validationRegExp.validationTokenRegExp(resetAdminRequest.token())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);

        } else {

            Admin admin = serviceJpa.findAdminByToken(resetAdminRequest.token()).orElse(new Admin());

            if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
                return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);
            } else {

                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


            }

        }


    }


    @PostMapping(value = "/newPasswordAdmin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> newPassword(@RequestBody ResetAdminRequest resetAdminRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (resetAdminRequest.password() != null
                && !resetAdminRequest.password().equals("")
                && resetAdminRequest.password().length() >= 6
                && resetAdminRequest.password().length() <= 24
                && validationRegExp.passwordValidationRegExp(resetAdminRequest.password())
                && !limitLogin.isBlocked(request.getRemoteAddr())
                && validationRegExp.validationTokenRegExp(resetAdminRequest.token())) {

            Admin admin = serviceJpa.findAdminByToken(resetAdminRequest.token()).orElse(new Admin());

            if (admin.getToken() != null && !admin.getToken().isEmpty()) {

                serviceJpa.updateAdminPasswordTokenByEmail(null, encoder.encode(resetAdminRequest.password()), admin.getEmail());
            }


        }
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


    }

}
