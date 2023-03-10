package com.sakhbord.bord.controllers.search;

import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.models.categories.Category;
import com.sakhbord.bord.models.city.City;
import com.sakhbord.bord.models.type.category.TypeCategory;
import com.sakhbord.bord.repository.service.ServiceJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sakhbord.bord.filter.CategoryTypeFilter.*;

@RestController
@RequestMapping("/api/data")
public class GetAdFromFilter {

    private final
    ServiceJpa serviceJpa;

    public GetAdFromFilter(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }

    @GetMapping(value = "/filter/announcement/{category}/{city}", produces = "application/json")
    ResponseEntity<Page<Announcement>> getAnnouncementFilter(@PathVariable(value = "category") int category,
                                                             @PathVariable(value = "city", required = false) int city) {


        if ((isCityNumber(city)) && (category == 110 || category == 121 || category == 132 || category == 190)) {

            Pageable pageable = PageRequest.of(0, 20);

            Page<Announcement> announcementPage =  serviceJpa.findAllByCategoryAndCity(new Category(getNumberCategory(category)), new City((long) city), pageable);

            return ResponseEntity.ok().body(announcementPage);
        }

        else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Page.empty());


        }


    }


    @GetMapping(value = "/filterType/announcement/{category}/{city}", produces = "application/json")
    ResponseEntity<Page<Announcement>> getAnnouncementFilterAll(@PathVariable(value = "category") int category,
                                                             @PathVariable(value = "city", required = false) int city) {


        if ((isCityNumber(city)) && selectDepartamentCode(category)) {

            Pageable pageable = PageRequest.of(0, 20);

            Page<Announcement> announcementPage =  serviceJpa.findAllByCategoryAndTypeCategoryAndCity(
                    new Category(selectCategoryCode(category)),
                    new TypeCategory(selectTypeCategoryCode(category)),
                    new City((long) city), pageable);

            return ResponseEntity.ok().body(announcementPage);
        }

        else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Page.empty());


        }


    }



}
