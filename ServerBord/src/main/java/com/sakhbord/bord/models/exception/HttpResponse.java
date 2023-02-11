package com.sakhbord.bord.models.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @param httpStatusCode 200, 201, 400, 500
 */
public record HttpResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Sakhalin") Date timeStamp,
        int httpStatusCode, HttpStatus httpStatus, String reason, String message) {

}
