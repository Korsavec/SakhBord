package com.sakhbord.bord.config;

import com.sakhbord.bord.models.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Constants {

    private Constants() {
    }



    public static final String SLASH = "/";
    public static final String DOT = ".";

    public static final String STATIC_OK = "OK";
    public static final String STATIC_NO = "no";
    public static final String STATIC_EMAIL = "email";

    public static ResponseEntity<MessageResponse> customMessageConfirm() {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.NOT_FOUND.value(),
                STATIC_OK),
                HttpStatus.OK);
    }

}
