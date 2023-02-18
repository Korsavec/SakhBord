package com.sakhbord.bord.models.payload.request.message;

public record AddMessageRequest(

        int selectCity,
        int selectDepartament,
        String message,
        Long phoneNumber,
        String emailAddress,
        String telegram

) {

    //

}
