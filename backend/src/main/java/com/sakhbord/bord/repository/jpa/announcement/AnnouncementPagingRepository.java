package com.sakhbord.bord.repository.jpa.announcement;

import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.models.categories.Category;
import com.sakhbord.bord.models.city.City;
import com.sakhbord.bord.models.type.category.TypeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnnouncementPagingRepository extends PagingAndSortingRepository<Announcement, Long> {

    Page<Announcement> findAllByCategoryAndCity(Category category, City city, Pageable pageable);

    Page<Announcement> findAllByCategoryAndTypeCategoryAndCity(Category category, TypeCategory typeCategory, City city, Pageable pageable);

}