package com.sakhbord.bord.controllers.add;

import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.models.categories.Category;
import com.sakhbord.bord.models.city.City;
import com.sakhbord.bord.models.payload.request.message.AddMessageRequest;
import com.sakhbord.bord.models.payload.response.MessageResponse;
import com.sakhbord.bord.models.type.category.TypeCategory;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.service.jpa.ServiceJpa;
import com.sakhbord.bord.validation.ValidationRegExp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sakhbord.bord.filter.CategoryTypeFilter.*;

@RestController
@RequestMapping("/api/AccountGuard")
public class AddMessageController {


    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS";

    private static final String MESSAGE_ERROR = "Internal Server Error";

    private final ServiceJpa serviceJpa;
    private final ValidationRegExp validationRegExp;


    public AddMessageController(ServiceJpa serviceJpa, ValidationRegExp validationRegExp) {
        this.serviceJpa = serviceJpa;
        this.validationRegExp = validationRegExp;
    }

    @PostMapping(value = "/addMessage", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> addMessage(@RequestBody AddMessageRequest addMessageRequest, HttpServletRequest request) {


        String cityRequest = selectCity(addMessageRequest.selectCity());
        String departamentRequest = selectDepartament(addMessageRequest.selectDepartament());
        String typeRequest = selectType(addMessageRequest.selectDepartament());
        String noType = selectNoType(addMessageRequest.selectDepartament());



        if (!(validationRegExp.patternAnnouncement(addMessageRequest.message())
                && !cityRequest.equals("")
                && !departamentRequest.equals("")
                && !(typeRequest.equals("") && noType.equals("type"))
                && !(addMessageRequest.emailAddress().equals("") && addMessageRequest.telegram().equals("")
                && addMessageRequest.phoneNumber() == null))) {
            return addError();
        }


        if (!validationRegExp.onlyNumbersWithEmptyRegExp(addMessageRequest.phoneNumber())
                || !validationRegExp.emailValidationWithEmptyRegExp(addMessageRequest.emailAddress())
                || !validationRegExp.telegramValidationWithEmptyRegExp(addMessageRequest.telegram())) {
            return addError();
        }

        if (addMessageRequest.emailAddress().equals("") && addMessageRequest.telegram().equals("")) {
            // Email и Telegram пусты
            // Phone ok
            return countAnnouncementPhone(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
        }



        if (addMessageRequest.phoneNumber() == null && addMessageRequest.telegram().equals("")) {
            // Phone и Telegram пусты
            // Email ok
            return countAnnouncementEmail(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
        }



        if (addMessageRequest.phoneNumber() == null && addMessageRequest.emailAddress().equals("")) {
            // Phone и Email пусты
            // Telegram ok
            return countAnnouncementTelegram(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
        }


        if (addMessageRequest.phoneNumber() == null) {
            // Phone пусто
            // Telegram и Email ok
            return countAnnouncementTelegramEmail(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
        }

        if (addMessageRequest.emailAddress().equals("")) {
            // Email пустой
            // Phone и Telegram ok
            return countAnnouncementPhoneTelegram(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
        }


        if (addMessageRequest.telegram().equals("")) {
            // Phone и Telegram ok
            return countAnnouncementPhoneEmail(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
        }

        // Phone, Email и Telegram НЕ пусты
        // Phone, Email и Telegram ok
        return addAnnouncementAllOpt(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);




    }




    public ResponseEntity<MessageResponse> countAnnouncementPhone(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        // Email и Telegram пусты

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementWithPhone(startDate, endDate,
                addMessageRequest.phoneNumber().toString(), request.getRemoteAddr());


        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setPhone(addMessageRequest.phoneNumber());
            serviceJpa.saveAnnouncement(announcement);

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);
        } else {

            return addError();

        }


    }






    public ResponseEntity<MessageResponse> countAnnouncementEmail(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        // Phone и Telegram пусты

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementWithEmail(startDate, endDate,
                addMessageRequest.emailAddress(),
                request.getRemoteAddr());

        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setEmail(addMessageRequest.emailAddress());
            serviceJpa.saveAnnouncement(announcement);

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);
        } else {

            return addError();

        }

    }







    public ResponseEntity<MessageResponse> countAnnouncementTelegram(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        // Phone и Email пусты

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementWithTelegram(startDate, endDate,
                addMessageRequest.telegram(), request.getRemoteAddr());

        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setEmail(addMessageRequest.telegram());
            serviceJpa.saveAnnouncement(announcement);

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);
        } else {

            return addError();

        }


    }







    public ResponseEntity<MessageResponse> countAnnouncementTelegramEmail(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        // Phone пусто

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementWithEmailTelegram(startDate, endDate,
                addMessageRequest.emailAddress(),
                addMessageRequest.telegram(), request.getRemoteAddr());

        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setEmail(addMessageRequest.emailAddress());
            announcement.setEmail(addMessageRequest.telegram());
            serviceJpa.saveAnnouncement(announcement);

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);
        } else {

            return addError();

        }



    }





    public ResponseEntity<MessageResponse> countAnnouncementPhoneTelegram(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        // Email пустой

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementWithPhoneTelegram(startDate, endDate,
                addMessageRequest.phoneNumber().toString(),
                addMessageRequest.telegram(), request.getRemoteAddr());

        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setPhone(addMessageRequest.phoneNumber());
            announcement.setEmail(addMessageRequest.telegram());
            serviceJpa.saveAnnouncement(announcement);

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);
        } else {

            return addError();

        }





    }

    public ResponseEntity<MessageResponse> countAnnouncementPhoneEmail(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        // Telegram пусто

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementWithPhoneEmail(startDate, endDate,
                addMessageRequest.phoneNumber().toString(), addMessageRequest.emailAddress(), request.getRemoteAddr());

        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setPhone(addMessageRequest.phoneNumber());
            announcement.setEmail(addMessageRequest.emailAddress());

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);

        } else {

            return addError();

        }


    }


    public ResponseEntity<MessageResponse> addAnnouncementAllOpt(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {

        String startDate = LocalDateTime.now().minusHours(24).format(DateTimeFormatter.ofPattern(FORMAT));
        String endDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT));

        String count = serviceJpa.countAnnouncementFull(startDate, endDate,
                addMessageRequest.phoneNumber().toString(), addMessageRequest.emailAddress(),
                addMessageRequest.telegram(), request.getRemoteAddr());


        if (Integer.parseInt(count) < 4) {

            Announcement announcement = buildAnnouncement(addMessageRequest, request, cityRequest, departamentRequest, typeRequest, noType);
            announcement.setPhone(addMessageRequest.phoneNumber());
            announcement.setEmail(addMessageRequest.emailAddress());
            announcement.setEmail(addMessageRequest.telegram());

            serviceJpa.saveAnnouncement(announcement);


            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);
        } else {

            return addError();

        }


    }




    public Announcement buildAnnouncement(AddMessageRequest addMessageRequest, HttpServletRequest request, String cityRequest, String departamentRequest, String typeRequest, String noType) {


        City city = serviceJpa.findCityByName(cityRequest).orElse(new City());
        Category category = serviceJpa.findCategoryByName(departamentRequest).orElse(new Category());
        TypeCategory typeCategory = serviceJpa.findTypeCategoryByName(typeRequest).orElse(new TypeCategory());

        String emailUser = request.getUserPrincipal().getName();

        User user = serviceJpa.findUserByEmail(emailUser).orElse(new User());

        Announcement announcement = new Announcement();
        announcement.setEnabled(true);
        announcement.setMessage(addMessageRequest.message());
        announcement.setCity(city);
        announcement.setCategory(category);
        announcement.setUser(user);
        announcement.setDateCreatedAnnouncement(LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT)));
        announcement.setIpAddressRegistration(request.getRemoteAddr());

        if (noType.equals("type")) {

            // Добавляем ещё тип и сохраняем
            announcement.setTypeCategory(typeCategory);
            return announcement;

        }
        // Возвращаем
        return announcement;




    }




    public ResponseEntity<MessageResponse> addError() {
        return new ResponseEntity<>(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), MESSAGE_ERROR),
                HttpStatus.OK);
    }




}
