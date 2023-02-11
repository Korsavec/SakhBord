package com.sakhbord.bord.service.jpa;

import com.sakhbord.bord.enam.RoleEnum;
import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.admin.Admin;
import com.sakhbord.bord.models.role.RoleAdmin;
import com.sakhbord.bord.models.role.RoleUser;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.repository.AdminRepository;
import com.sakhbord.bord.repository.UserRepository;
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


    private final RoleAdminRepository roleAdminRepository;
    private final RoleUserRepository roleUserRepository;



    private final NotActivatedUserRepository notActivatedUserRepository;

    public ServiceJpaImpl(AdminRepository adminRepository, UserRepository userRepository, RoleAdminRepository roleAdminRepository, RoleUserRepository roleUserRepository, NotActivatedUserRepository notActivatedUserRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.roleAdminRepository = roleAdminRepository;
        this.roleUserRepository = roleUserRepository;
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


}
