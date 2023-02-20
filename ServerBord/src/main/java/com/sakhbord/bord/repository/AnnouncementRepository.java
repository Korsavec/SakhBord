package com.sakhbord.bord.repository;

import com.sakhbord.bord.models.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {


    // https://www.baeldung.com/spring-data-jpa-modifying-annotation

    // Аннотация @Modifying используется для улучшения аннотации @Query, чтобы мы могли
    // выполнять не только запросы SELECT, но также запросы INSERT, UPDATE, DELETE и даже DDL.

    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.phone = :phone or a.telegram = :telegram or a.email = :email or a.ipAddressRegistration = :ip)")
    String countAnnouncementFull(@Param("startDate") String startTime,
                                 @Param("endDate") String endTime,
                                 @Param("phone") String phone,
                                 @Param("telegram") String telegram,
                                 @Param("email") String email,
                                 @Param("ip") String ip);

    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.telegram = :telegram or a.email = :email or a.ipAddressRegistration = :ip)")
    String countAnnouncementWithEmailTelegram(@Param("startDate") String startTime,
                                              @Param("endDate") String endTime,
                                              @Param("telegram") String telegram,
                                              @Param("email") String email,
                                              @Param("ip") String ip);

    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.phone = :phone or a.telegram = :telegram or a.ipAddressRegistration = :ip)")
    String countAnnouncementWithPhoneTelegram(@Param("startDate") String startTime,
                                              @Param("endDate") String endTime,
                                              @Param("phone") String phone,
                                              @Param("telegram") String telegram,
                                              @Param("ip") String ip);


    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.phone = :phone or a.email = :email or a.ipAddressRegistration = :ip)")
    String countAnnouncementWithPhoneEmail(@Param("startDate") String startTime,
                                            @Param("endDate") String endTime,
                                            @Param("phone") String phone,
                                            @Param("email") String email,
                                            @Param("ip") String ip);


    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.phone = :phone or a.ipAddressRegistration = :ip)")
    String countAnnouncementWithPhone(@Param("startDate") String startTime,
                                      @Param("endDate") String endTime,
                                      @Param("phone") String phone,
                                      @Param("ip") String ip);


    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.email = :email or a.ipAddressRegistration = :ip)")
    String countAnnouncementWithEmail(@Param("startDate") String startTime,
                                      @Param("endDate") String endTime,
                                      @Param("email") String email,
                                      @Param("ip") String ip);


    @Query("select count(*) as count from Announcement as a where a.dateCreatedAnnouncement between :startDate and :endDate " +
            "and (a.telegram = :telegram or a.ipAddressRegistration = :ip)")
    String countAnnouncementWithTelegram(@Param("startDate") String startTime,
                                         @Param("endDate") String endTime,
                                         @Param("telegram") String telegram,
                                         @Param("ip") String ip);




}