package com.sakhbord.bord.controllers.delete.announcement;

import com.sakhbord.bord.repository.service.ServiceJpa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/AccountGuard")
public class DeleteAnnouncement {

    private final
    ServiceJpa serviceJpa;

    public DeleteAnnouncement(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }

    @GetMapping(value = "/deleteAnnouncement", produces = "application/json", consumes = "application/json")
    public String deleteAnnouncement() {



        serviceJpa.deleteAnnouncementById(2L);

        return "{\"message\": \"OK\"}";
    }


    @GetMapping(value = "/deleteUser", produces = "application/json", consumes = "application/json")
    public String deleteUser() {



        serviceJpa.deleteUserById(1L);

        return "{\"message\": \"OK\"}";
    }

}
