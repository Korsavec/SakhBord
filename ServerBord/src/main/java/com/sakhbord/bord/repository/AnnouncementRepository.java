package com.sakhbord.bord.repository;

import com.sakhbord.bord.models.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}