package com.sakhbord.bord.controllers.reset;

import com.sakhbord.bord.email.SendEmail;
import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.payload.request.user.ResetUserRequest;
import com.sakhbord.bord.models.payload.response.MessageResponse;
import com.sakhbord.bord.models.user.User;
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
@RequestMapping("/api/auth")
public class ResetPasswordUserController {

  @Value("${sakhshop.app.main.site.domain.user}")
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

  public ResetPasswordUserController(SendEmail sendEmail, ValidationRegExp validationRegExp, LoginAttemptService limitLogin, PasswordEncoder encoder, ServiceJpa serviceJpa) {
    this.sendEmail = sendEmail;
    this.validationRegExp = validationRegExp;
    this.limitLogin = limitLogin;
    this.encoder = encoder;
    this.serviceJpa = serviceJpa;
  }


  @PostMapping(value = "/resetPasswordUser", consumes = "application/json", produces = "application/json")
  public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetUserRequest resetUserRequest, HttpServletRequest request) {

    limitLogin.addCache(request.getRemoteAddr());

    if (resetUserRequest.email() != null
            && !resetUserRequest.email().equals("")
            && resetUserRequest.email().length() >= 8
            && resetUserRequest.email().length() <= 58
            && validationRegExp.emailValidationRegExp(resetUserRequest.email())
            && !limitLogin.isBlocked(request.getRemoteAddr())
            && Boolean.TRUE.equals(serviceJpa.existsUserByEmail(resetUserRequest.email()))) {

      // Создаём token для ссылки
      String token = UUID.randomUUID().toString();

      serviceJpa.updateUserTokenByEmail(token, resetUserRequest.email());

      String urlResetPasswordUser = siteDomain + "/resetPassword/newPassword/" + token;
      sendEmail.resetPasswordUser(request.getServerName(), urlResetPasswordUser);

    }
    return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


  }


  @PostMapping(value = "/checkTokenUserResetPassword", consumes = "application/json", produces = "application/json")
  public ResponseEntity<MessageResponse> checkServerToken(@RequestBody ResetUserRequest resetUserRequest, HttpServletRequest request) {

    limitLogin.addCache(request.getRemoteAddr());

    if (resetUserRequest.token() == null
            || resetUserRequest.token().equals("")
            || resetUserRequest.token().length() != 36
            || limitLogin.isBlocked(request.getRemoteAddr())
            || !validationRegExp.validationTokenRegExp(resetUserRequest.token())) {
      return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);

    } else {

     User user = serviceJpa.findUserByToken(resetUserRequest.token()).orElse(new User());

      if (user.getEmail() == null || user.getEmail().isEmpty()) {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),STATIC_NO), HttpStatus.OK);
      } else {


        // Проверяем, подтвердил ли пользователь свой email адрес.
        // Если адрес не подтверждён, то подтверждаем и активируем.
        if (user.isConfirmationEmail()) {

          user.setEnabled(true);
          user.setConfirmationEmail(true);

          NotActivatedUser notActivatedUser = user.getNotActivatedUser();
          notActivatedUser.setActive(true);
          user.setNotActivatedUser(notActivatedUser);

          serviceJpa.saveUser(user);

        }

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


      }

    }


  }


  @PostMapping(value = "/newPasswordUser", consumes = "application/json", produces = "application/json")
  public ResponseEntity<MessageResponse> newPassword(@RequestBody ResetUserRequest resetUserRequest, HttpServletRequest request) {

    limitLogin.addCache(request.getRemoteAddr());

    if (resetUserRequest.password() != null
            && !resetUserRequest.password().equals("")
            && resetUserRequest.password().length() >= 6
            && resetUserRequest.password().length() <= 24
            && validationRegExp.passwordValidationRegExp(resetUserRequest.password())
            && !limitLogin.isBlocked(request.getRemoteAddr())
            && validationRegExp.validationTokenRegExp(resetUserRequest.token())) {

      User user = serviceJpa.findUserByToken(resetUserRequest.token()).orElse(new User());

      if (user.getToken() != null && !user.getToken().isEmpty()) {

        serviceJpa.updateUserPasswordTokenByEmail(null, encoder.encode(resetUserRequest.password()), user.getEmail());
      }


    }
    return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),STATIC_OK), HttpStatus.OK);


  }
}
