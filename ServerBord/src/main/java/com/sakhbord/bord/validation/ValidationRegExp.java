package com.sakhbord.bord.validation;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationRegExp {

    private static final Pattern patternAnnouncement = Pattern.compile("^[0-9а-яА-ЯёЁA-Za-z-/\\\\,. =+«»:;*\"!№#$%@?()]+$");

    private static final Pattern onlyLettersCyrillic = Pattern.compile("^[а-яА-Я]+$");

    private static final Pattern onlyNumbers = Pattern.compile("^\\d+$");

    private static final Pattern onlyLettersCyrillicAndNumbers = Pattern.compile("^[0-9а-яА-Я]+$");

//    ^(|(([A-Za-z0-9]{1,25}_)|([A-Za-z0-9]{1,25}-)|([A-Za-z0-9]{1,25}\\.))*[A-Za-z0-9]{1,25}@(([A-Za-z0-9]{1,25}\\.)|([A-Za-z0-9]{1,25}-))*[A-Za-z0-9]{2,25}\\.[a-zA-Z]{2,6})$
    private static final Pattern patternEmail = Pattern.compile("^[\\w.-]*@[\\w-]*+.+\\w$");

    private static final Pattern patternPassword = Pattern.compile("^[0-9a-zA-Z@#$]+$");

    private static final Pattern patternDate = Pattern.compile("^(0[1-9]|[12]\\d|3[01])[- /.](0[1-9]|1[012])[- /.](19|20|29)\\d\\d$");



    //Pattern RegExp для проверки token-а
    private static final Pattern patternTokenConfirm = Pattern.compile("^([a-z0-9]{8})-([a-z0-9]{4})-([a-z0-9]{4})-([a-z0-9]{4})-([a-z0-9]{12})$");


    private static final Pattern patternTelegram = Pattern.compile("^[\\w]+$");


    public boolean patternAnnouncement(String value) {

        if (value.equals("")) {
            return false;
        } else if (value.length() < 120 || value.length() > 335) {
            return false;
        } else {
            Matcher matcherTelegram = patternAnnouncement.matcher(value);

            // Если валидацию прошли, то true, то есть, ошибок нет
            return matcherTelegram.matches();
        }



    }


    public boolean telegramValidationRegExp(String value) {

        Matcher matcherTelegram = patternTelegram.matcher(value);

        // Если валидацию прошли, то true, то есть, ошибок нет
        return matcherTelegram.matches();
    }

    public boolean telegramValidationWithEmptyRegExp(String value) {

        if (value.equals("")) {
            return true;
        } else if (value.length() < 5 || value.length() > 32) {
            return false;
        } else {
            Matcher matcherTelegram = patternTelegram.matcher(value);

            // Если валидацию прошли, то true, то есть, ошибок нет
            return matcherTelegram.matches();
        }


    }

    public boolean onlyNumbersRegExp(String phone) {

        Matcher matcherPhone = onlyNumbers.matcher(phone);

        // Если валидацию прошли, то true, то есть, ошибок нет
        return matcherPhone.matches();
    }

    public boolean onlyNumbersWithEmptyRegExp(Long phone) {

        if (phone == null) {
            return true;
        } else if (phone != 10) {
            return false;

        } else {
            String phomeString = phone.toString();
            Matcher matcherPhone = onlyNumbers.matcher(phomeString);

            // Если валидацию прошли, то true, то есть, ошибок нет
            return matcherPhone.matches();
        }


    }


    public boolean emailValidationRegExp(String email) {

        Matcher matcherEmail = patternEmail.matcher(email);

        return matcherEmail.matches();
    }

    public boolean emailValidationWithEmptyRegExp(String email) {

        if (email.equals("")) {
            return true;
        } else if (email.length() < 8 || email.length() > 58) {
            return false;
        } else {
            Matcher matcherEmail = patternEmail.matcher(email);

            return matcherEmail.matches();
        }


    }


    public boolean passwordValidationRegExp(String password) {

        Matcher matcherPassword = patternPassword.matcher(password);

        return matcherPassword.matches();
    }


    public boolean onlyLettersCyrillic(String username) {

        Matcher matcherPassword = onlyLettersCyrillic.matcher(username);

        return matcherPassword.matches();
    }



    public boolean validationTokenRegExp (String token) {

        Matcher matcher = patternTokenConfirm.matcher(token);

        return matcher.matches();
    }


    public boolean validationDateRegExp (String date) {


        Matcher matcher = patternDate.matcher(date);

        return matcher.matches();

    }


    public boolean onlyLettersCyrillicAndNumbersRegExp(String token) {

        Matcher matcher = onlyLettersCyrillicAndNumbers.matcher(token);

        return matcher.matches();
    }



}
