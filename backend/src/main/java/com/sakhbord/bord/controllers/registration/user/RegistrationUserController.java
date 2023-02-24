package com.sakhbord.bord.controllers.registration.user;

import com.sakhbord.bord.email.SendEmail;
import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.payload.request.user.RegistrationUserRequest;
import com.sakhbord.bord.models.payload.response.MessageResponse;
import com.sakhbord.bord.models.role.RoleUser;
import com.sakhbord.bord.models.token.Token;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.repository.service.ServiceJpa;
import com.sakhbord.bord.service.cache.LoginAttemptService;
import com.sakhbord.bord.service.cache.RegistrationAttemptService;
import com.sakhbord.bord.validation.ValidationRegExp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static com.sakhbord.bord.config.Constants.STATIC_OK;
import static com.sakhbord.bord.config.Constants.customMessageConfirm;

@RestController
@RequestMapping("/api")
public class RegistrationUserController {

    private final
    SendEmail sendEmail;

    private final
    PasswordEncoder encoder;

    private final
    ValidationRegExp validationRegExp;

    private final
    LoginAttemptService limitLogin;

    private final
    RegistrationAttemptService registrationAttemptService;

    private final
    ServiceJpa serviceJpa;

    public RegistrationUserController(PasswordEncoder encoder, ValidationRegExp validationRegExp, SendEmail sendEmail, LoginAttemptService limitLogin, RegistrationAttemptService registrationAttemptService, ServiceJpa serviceJpa) {
        this.encoder = encoder;
        this.validationRegExp = validationRegExp;
        this.sendEmail = sendEmail;
        this.limitLogin = limitLogin;
        this.registrationAttemptService = registrationAttemptService;
        this.serviceJpa = serviceJpa;
    }

    @PostMapping(value = "/registrationUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegistrationUserRequest registrationUserRequest, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (registrationUserRequest.email().length() < 8
                || registrationUserRequest.email().length() > 58
                || registrationUserRequest.password().length() < 6
                || registrationUserRequest.password().length() > 24
                || limitLogin.isBlocked(request.getRemoteAddr())
                || registrationAttemptService.isBlocked(request.getRemoteAddr())
                || !validationRegExp.emailValidationRegExp(registrationUserRequest.email())
                || !validationRegExp.passwordValidationRegExp(registrationUserRequest.password())) {

            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),
                    STATIC_OK),
                    HttpStatus.OK);
        }

        if (Boolean.TRUE.equals(serviceJpa.existsUserByEmail(registrationUserRequest.email()))) {

            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    "User exist"),
                    HttpStatus.BAD_REQUEST);
        }

        registrationAttemptService.addCache(request.getRemoteAddr());

        User user = new User();
        user.setEmail(registrationUserRequest.email());
        user.setPassword(registrationUserRequest.password());
        user.setPassword(encoder.encode(user.getPassword()));

        user.setToken(UUID.randomUUID().toString());
        user.setEnabled(false);
        user.setAccountNonLocked(true);
        user.setDateCreatedUser(Instant.now());
        user.setIpAddressRegistration(request.getRemoteAddr());

        // Добавляем дату удаления аккаунта если не будет подтверждён адрес электронной почты,
        NotActivatedUser notActivatedUser = new NotActivatedUser();
        notActivatedUser.setDateDeletionUser(Instant.now().plus(1, ChronoUnit.DAYS));
        notActivatedUser.setActive(false);

        // Синхронизируем между собой user и notActivatedUser или по простому - соединяем между собой
        user.setNotActivatedUser(notActivatedUser);
        notActivatedUser.setUser(user);


        Set<RoleUser> roleUsers = new LinkedHashSet<>();
        RoleUser roleUser = serviceJpa.findRoleUserByName("ROLE_USER");
        roleUsers.add(roleUser);
        user.setRoleUsers(roleUsers);
        serviceJpa.saveUser(user);

        // Отправляем письмо пользователю
        sendEmail.confirmEmailUser(request.getServerName(), user.getToken());

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

    }


    @PostMapping(value = "/confirmEmailUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> confirmEmail(@RequestBody Token token, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (token.token().length() != 36 || !validationRegExp.validationTokenRegExp(token.token()) || limitLogin.isBlocked(request.getRemoteAddr())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    STATIC_OK),
                    HttpStatus.OK);
        }

        User user = serviceJpa.findUserByToken(token.token()).orElse(new User());

        if (user.getToken() == null || user.getToken().isEmpty()) {
            return customMessageConfirm();
        }

        user.setConfirmationEmail(true);
        user.setEnabled(true);
        user.setToken(null);

        NotActivatedUser notActivatedUser = user.getNotActivatedUser();

        notActivatedUser.setActive(true);

        notActivatedUser.setUser(user);
        user.setNotActivatedUser(notActivatedUser);

        serviceJpa.saveUser(user);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

    }

}
