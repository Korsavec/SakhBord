package com.sakhbord.bord.controllers.search;

import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.models.payload.request.search.SearchRequest;
import com.sakhbord.bord.repository.service.ServiceJpa;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data/search")
public class Search {


    private final
    ServiceJpa serviceJpa;

    public Search(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }

    @GetMapping(value = "/announcement", produces = "application/json")
    ResponseEntity<Page<Announcement>> getAnnouncementFilter(@RequestBody SearchRequest searchRequest) {

        return null;


    }


}
