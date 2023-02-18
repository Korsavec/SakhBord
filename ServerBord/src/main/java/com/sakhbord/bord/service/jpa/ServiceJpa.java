package com.sakhbord.bord.service.jpa;

import com.sakhbord.bord.enam.RoleEnum;
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

    RoleUser findRoleUserByRoleEnum(RoleEnum roleEnum);

    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Transactional
    void deleteListUser(Iterable<User> entityList);

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

    RoleAdmin findRoleAdminByRoleEnum(RoleEnum roleEnum);

    // Admin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    Boolean existsAdminByEmail(String email);

    Optional<Admin> findAdminByEmail(String email);

    Optional<Admin> findAdminByToken (String token);

    @Transactional
    void updateAdminPasswordTokenByEmail(String token, String password, String email);

    @Transactional
    void updateAdminTokenByEmail(String token, String email);



    // >>>>>>>>>>>>>>>>>>>> AddMessage USER <<<<<<<<<<<<<<<<<<<<

    @Transactional
    void saveAnnouncement(Announcement announcement);




    // >>>>>>>>>>>>>>>>>>>> CityRepository <<<<<<<<<<<<<<<<<<<<


    Optional<City> findCityByName(String name);


    // >>>>>>>>>>>>>>>>>>>> CategoryRepository <<<<<<<<<<<<<<<<<<<<


    Optional<Category> findCategoryByName(String name);


    // >>>>>>>>>>>>>>>>>>>> TypeCategoryRepository <<<<<<<<<<<<<<<<<<<<


    Optional<TypeCategory> findTypeCategoryByName(String name);

}
