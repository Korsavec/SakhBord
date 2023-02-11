package com.sakhbord.bord.exception;

import com.sakhbord.bord.exception.domain.EmailExistException;
import com.sakhbord.bord.exception.domain.EmailNotFoundException;
import com.sakhbord.bord.exception.domain.UserNotFoundException;
import com.sakhbord.bord.exception.domain.UsernameExistException;
import com.sakhbord.bord.models.exception.HttpResponse;
import jakarta.persistence.NoResultException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(new Date(), httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

    // + AuthTokenFilter
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponse> usernameNotFoundException(UsernameNotFoundException e) {
        return createHttpResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // + isEnabled
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST, "Ваш аккаунт не активирован.");
    }

    // +
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, "Неверный логин или пароль.");
    }

    // Например, если пользователь пытается получить доступ к admin части
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, "У вас недостаточно прав.");
    }

    // + isAccountNonLocked
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, "Ваш аккаунт заблокирован.");
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerFoundException() {
        return createHttpResponse(BAD_REQUEST, "Для этого URL нет сопоставления");
    }


    // + Если отправляю запрос на не правильные тип метода, например надо GET, а отправляю POST
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format("Этот метод запроса не разрешен. Пожалуйста, используйте '%s' запрос", supportedMethod));
    }

    // + Возникала когда отправлял http запрос на неверный адрес для подтверждения email
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException() {
        return createHttpResponse(INTERNAL_SERVER_ERROR, "Произошла ошибка при обработке запроса");
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(URISyntaxException.class)
    public ResponseEntity<HttpResponse> uriSyntaxException() {
        return createHttpResponse(INTERNAL_SERVER_ERROR, "Что то пошло не так, попробуйте позже");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> ioException() {
        return createHttpResponse(INTERNAL_SERVER_ERROR, "Что то пошло не так, попробуйте позже");
    }



}
