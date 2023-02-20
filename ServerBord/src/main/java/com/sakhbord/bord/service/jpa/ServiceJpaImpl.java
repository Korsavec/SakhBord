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
import com.sakhbord.bord.repository.*;
import com.sakhbord.bord.repository.activated.NotActivatedUserRepository;
import com.sakhbord.bord.repository.role.RoleAdminRepository;
import com.sakhbord.bord.repository.role.RoleUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServiceJpaImpl implements ServiceJpa {


    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    private final AnnouncementRepository announcementRepository;


    private final RoleAdminRepository roleAdminRepository;
    private final RoleUserRepository roleUserRepository;


    private final CityRepository cityRepository;
    private final CategoryRepository categoryRepository;
    private final TypeCategoryRepository typeCategoryRepository;



    private final NotActivatedUserRepository notActivatedUserRepository;

    public ServiceJpaImpl(AdminRepository adminRepository, UserRepository userRepository, AnnouncementRepository announcementRepository, RoleAdminRepository roleAdminRepository, RoleUserRepository roleUserRepository, CityRepository cityRepository, CategoryRepository categoryRepository, TypeCategoryRepository typeCategoryRepository, NotActivatedUserRepository notActivatedUserRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.roleAdminRepository = roleAdminRepository;
        this.roleUserRepository = roleUserRepository;
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
        this.typeCategoryRepository = typeCategoryRepository;
        this.notActivatedUserRepository = notActivatedUserRepository;
    }

    // >>>>>>>>>>>>>>>>>>>> User <<<<<<<<<<<<<<<<<<<<





    // NotActivatedUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    @Transactional
    public void deleteAllUser(Iterable<NotActivatedUser> entityList) {
        notActivatedUserRepository.deleteAll(entityList);
    }

    @Override
    public Iterable<NotActivatedUser> findAllDateDeletionUser() {
        return notActivatedUserRepository.findAll();
    }

    // RoleUser >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleUser findRoleUserByRoleEnum(RoleEnum roleEnum) {
        return roleUserRepository.findRoleUserByRoleEnum(roleEnum);
    }

    // User >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    @Transactional
    public void deleteListUser(Iterable<User> entityList) {
        userRepository.deleteAll(entityList);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public List<User> findAllUserById(Iterable<Long> entityList) {
        return userRepository.findAllById(entityList);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUserPasswordTokenByEmail(String token, String password, String email) {
        userRepository.updateUserPasswordTokenByEmail(token, password, email);
    }

    @Transactional
    @Override
    public void updateUserTokenByEmail(String token, String email) {
        userRepository.updateUserTokenByEmail(token, email);
    }





    // >>>>>>>>>>>>>>>>>>>> Admin <<<<<<<<<<<<<<<<<<<<





    // RoleAdmin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public RoleAdmin findRoleAdminByRoleEnum(RoleEnum roleEnum) {
        return roleAdminRepository.findRoleAdminByRoleEnum(roleEnum);
    }

    // // Admin >>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Override
    public Boolean existsAdminByEmail(String email) {
        return adminRepository.existsAdminByEmail(email);
    }

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

    @Override
    public Optional<Admin> findAdminByToken(String token) {
        return adminRepository.findAdminByToken(token);
    }

    @Override
    public void updateAdminPasswordTokenByEmail(String token, String password, String email) {
        adminRepository.updateAdminPasswordTokenByEmail(token, password, email); // ++++++++++++++++++
    }

    @Override
    public void updateAdminTokenByEmail(String token, String email) {
        adminRepository.updateAdminTokenByEmail(token, email);
    }




    // >>>>>>>>>>>>>>>>>>>> Announcement USER <<<<<<<<<<<<<<<<<<<<


    @Override
    @Transactional
    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }


    @Override
    public String countAnnouncementFull(String startTime, String endTime, String phone, String telegram, String email, String ip) {
        return announcementRepository.countAnnouncementFull(startTime, endTime, phone, telegram, email, ip);
    }

    @Override
    public String countAnnouncementWithEmailTelegram(String startTime, String endTime, String telegram, String email, String ip) {
        return announcementRepository.countAnnouncementWithEmailTelegram(startTime, endTime, telegram, email, ip);
    }

    @Override
    public String countAnnouncementWithPhoneTelegram(String startTime, String endTime, String phone, String telegram, String ip) {
        return announcementRepository.countAnnouncementWithPhoneTelegram(startTime, endTime, phone, telegram, ip);
    }

    @Override
    public String countAnnouncementWithPhoneEmail(String startTime, String endTime, String phone, String email, String ip) {
        return announcementRepository.countAnnouncementWithPhoneEmail(startTime, endTime, phone, email, ip);
    }

    @Override
    public String countAnnouncementWithPhone(String startTime, String endTime, String phone, String ip) {
        return announcementRepository.countAnnouncementWithPhone(startTime, endTime, phone, ip);
    }

    @Override
    public String countAnnouncementWithEmail(String startTime, String endTime, String email, String ip) {
        return announcementRepository.countAnnouncementWithEmail(startTime, endTime, email, ip);
    }

    @Override
    public String countAnnouncementWithTelegram(String startTime, String endTime, String telegram, String ip) {
        return announcementRepository.countAnnouncementWithTelegram(startTime, endTime, telegram, ip);
    }


    // >>>>>>>>>>>>>>>>>>>> CityRepository <<<<<<<<<<<<<<<<<<<<


    @Override
    public Optional<City> findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }


    // >>>>>>>>>>>>>>>>>>>> CategoryRepository <<<<<<<<<<<<<<<<<<<<


    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }


    // >>>>>>>>>>>>>>>>>>>> TypeCategoryRepository <<<<<<<<<<<<<<<<<<<<


    @Override
    public Optional<TypeCategory> findTypeCategoryByName(String name) {
        return typeCategoryRepository.findTypeCategoryByName(name);
    }



}
