package com.sakhbord.bord.repository.service;

import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.admin.Admin;
import com.sakhbord.bord.models.announcement.Announcement;
import com.sakhbord.bord.models.categories.Category;
import com.sakhbord.bord.models.city.City;
import com.sakhbord.bord.models.role.RoleAdmin;
import com.sakhbord.bord.models.role.RoleUser;
import com.sakhbord.bord.models.type.category.TypeCategory;
import com.sakhbord.bord.models.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public interface ServiceJpa {



    // >>>>>>>>>>>>>>>>>>>> User <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteAllUser(Iterable<NotActivatedUser> entityList);

    Iterable<NotActivatedUser> findAllDateDeletionUser();

    // RoleUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    RoleUser findRoleUserByName(String value);

    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteListUser(Iterable<User> entityList);
    void deleteUserById(Long value);

    Boolean existsUserByEmail(String email);

    List<User> findAllUserById(Iterable<Long> entityList);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByToken (String token);

    @Transactional
    void saveUser(User user);

    @Transactional
    void updateUserPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateUserTokenByEmail(String token, String email);





    // >>>>>>>>>>>>>>>>>>>> Admin <<<<<<<<<<<<<<<<<<<<





    // RoleAdmin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    RoleAdmin findRoleAdminByName(String value);

    // Admin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    Boolean existsAdminByEmail(String email);

    Optional<Admin> findAdminByEmail(String email);

    Optional<Admin> findAdminByToken (String token);

    @Transactional
    void updateAdminPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateAdminTokenByEmail(String token, String email);



    // >>>>>>>>>>>>>>>>>>>> Announcement USER <<<<<<<<<<<<<<<<<<<<

    @Transactional
    void saveAnnouncement(Announcement announcement);
    void deleteAnnouncementById(Long value);


    String countAnnouncementFull (String startTime, String endTime, String phone, String telegram, String email, String ip);
    String countAnnouncementWithEmailTelegram (String startTime, String endTime, String telegram, String email, String ip);
    String countAnnouncementWithPhoneTelegram (String startTime, String endTime, String phone, String telegram, String ip);
    String countAnnouncementWithPhoneEmail (String startTime, String endTime, String phone, String email, String ip);
    String countAnnouncementWithPhone (String startTime, String endTime, String phone, String ip);
    String countAnnouncementWithEmail (String startTime, String endTime, String email, String ip);
    String countAnnouncementWithTelegram (String startTime, String endTime, String telegram, String ip);

    Optional<Announcement> announcementFindList(Long value);
    List<Announcement> announcementFindAll();




    // >>>>>>>>>>>>>>>>>>>> CityRepository <<<<<<<<<<<<<<<<<<<<


    Optional<City> findCityByName(String name);


    // >>>>>>>>>>>>>>>>>>>> CategoryRepository <<<<<<<<<<<<<<<<<<<<


    Optional<Category> findCategoryByName(String name);


    // >>>>>>>>>>>>>>>>>>>> TypeCategoryRepository <<<<<<<<<<<<<<<<<<<<


    Optional<TypeCategory> findTypeCategoryByName(String name);

}
