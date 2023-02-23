package com.sakhbord.bord.controllers.login.admin;

import com.sakhbord.bord.models.payload.request.admin.LoginAdminRequest;
import com.sakhbord.bord.models.payload.response.MessageResponse;
import com.sakhbord.bord.security.jwt.JwtUtils;
import com.sakhbord.bord.service.cache.LoginAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginAdminController {


    private final
    AuthenticationManager authenticationManager;

    private final
    JwtUtils jwtUtils;

    private final
    LoginAttemptService limitLogin;

    public LoginAdminController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, LoginAttemptService limitLogin) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.limitLogin = limitLogin;
    }

    @PostMapping(value = "/loginAdmin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> authenticateAdmin(@RequestBody LoginAdminRequest loginAdminRequest, HttpServletRequest request) {


        if (limitLogin.isBlocked(request.getRemoteAddr())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    "Что-то пошло не так. Попробуйте позе"),
                    HttpStatus.BAD_REQUEST);
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginAdminRequest.email(), loginAdminRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtTokenAdmin(authentication, loginAdminRequest.checkbox());
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), jwt),
                    HttpStatus.OK);

        }

    }

}
