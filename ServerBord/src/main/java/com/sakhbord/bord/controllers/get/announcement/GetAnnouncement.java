package com.sakhbord.bord.controllers.get.announcement;

import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.repository.service.ServiceJpa;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/AccountGuard")
public class GetAnnouncement {

    private final
    ServiceJpa serviceJpa;

    public GetAnnouncement(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }

    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @GetMapping(value = "/getAnnouncement", produces = "application/json", consumes = "application/json")
    public List<Announcement> getAnnouncement() {


        return serviceJpa.announcementFindAll();
    }

}
