package com.sakhbord.bord.controllers.add;

import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.models.categories.Category;
import com.sakhbord.bord.models.city.City;
import com.sakhbord.bord.models.payload.request.message.AddMessageRequest;
import com.sakhbord.bord.models.payload.response.MessageResponse;
import com.sakhbord.bord.models.type.category.TypeCategory;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.service.jpa.ServiceJpa;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.sakhbord.bord.filter.CategoryTypeFilter.*;

@RestController
@RequestMapping("/api/AccountGuard")
public class AddMessageController {

    private final ServiceJpa serviceJpa;


    public AddMessageController(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }

    @PostMapping(value = "/addMessage", produces = "application/json")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageResponse> addMessage(@RequestBody AddMessageRequest addMessageRequest, HttpServletRequest request) {

        String cityRequest = selectCity(addMessageRequest.selectCity());
        String departamentRequest = selectDepartament(addMessageRequest.selectDepartament());
        String typeRequest = selectType(addMessageRequest.selectDepartament());
        String noType = selectNoType(addMessageRequest.selectDepartament());

        if (cityRequest.equals("") || departamentRequest.equals("") || (typeRequest.equals("") && noType.equals("type"))) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
                    HttpStatus.OK);
        } else {

            if (noType.equals("noType")) {

                City city = serviceJpa.findCityByName(cityRequest).orElse(new City());
                Category category = serviceJpa.findCategoryByName(departamentRequest).orElse(new Category());

                String emailUser = request.getUserPrincipal().getName();
                User user = serviceJpa.findUserByEmail(emailUser).orElse(new User());


                Announcement announcement = new Announcement();
                announcement.setMessage(addMessageRequest.message());
                announcement.setPhone(addMessageRequest.phoneNumber());
                announcement.setEmail(addMessageRequest.emailAddress());
                announcement.setTelegram(addMessageRequest.telegram());
                announcement.setCity(city);
                announcement.setCategory(category);
                announcement.setUser(user);
                announcement.setDateCreatedAnnouncement(new Date().toInstant());
                announcement.setIpAddressRegistration(request.getRemoteAddr());

                serviceJpa.saveAnnouncement(announcement);

            } else {

                City city = serviceJpa.findCityByName(cityRequest).orElse(new City());
                Category category = serviceJpa.findCategoryByName(departamentRequest).orElse(new Category());
                TypeCategory typeCategory = serviceJpa.findTypeCategoryByName(typeRequest).orElse(new TypeCategory());


                String emailUser = request.getUserPrincipal().getName();
                User user = serviceJpa.findUserByEmail(emailUser).orElse(new User());


                Announcement announcement = new Announcement();
                announcement.setMessage(addMessageRequest.message());
                announcement.setPhone(addMessageRequest.phoneNumber());
                announcement.setEmail(addMessageRequest.emailAddress());
                announcement.setTelegram(addMessageRequest.telegram());
                announcement.setCity(city);
                announcement.setCategory(category);
                announcement.setUser(user);
                announcement.setDateCreatedAnnouncement(new Date().toInstant());
                announcement.setIpAddressRegistration(request.getRemoteAddr());
                announcement.setTypeCategory(typeCategory);

                serviceJpa.saveAnnouncement(announcement);

            }




            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "OK"),
                    HttpStatus.OK);

        }


    }

}
