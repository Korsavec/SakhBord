package com.sakhbord.bord.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

    private static final String CONFIRM_EMAIL = "/registration/confirmEmail/";

    @Value("${sakhshop.app.main.site.domain.user}")
    String siteDomainUser;


    private final
    JavaMailSender javaMailSender;


    @Value("${sakhshop.app.mail.email}")
    private String email;

    public SendEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public final void confirmEmailUser(String serverName, final String token){

        String path = siteDomainUser + CONFIRM_EMAIL + token;

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Регистрация на сайте " + serverName);
        msg.setText("Для подтверждения электронно почты и активации аккаунта, вам необходимо пройти по ссылке " + path);

        javaMailSender.send(msg);
    }






    public final void resetPasswordUser(String serverName, final String urlResetPasswordUserAccount){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Сброс пароля пользователя на сайте " + serverName);
        msg.setText("Для сброса пароля пользователя пройдите по ссылке "+ urlResetPasswordUserAccount);

        javaMailSender.send(msg);
    }



    public final void resetPasswordAdmin(String serverName, final String urlResetPasswordAdminAccount){

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email); // Кому
        msg.setFrom(email); // от кого

        msg.setSubject("Сброс пароля администратора на сайте " + serverName);
        msg.setText("Для сброса пароля администратора пройдите по ссылке "+ urlResetPasswordAdminAccount);

        javaMailSender.send(msg);
    }


}
