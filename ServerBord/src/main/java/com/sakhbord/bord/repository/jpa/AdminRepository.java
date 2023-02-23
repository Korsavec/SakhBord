package com.sakhbord.bord.repository.jpa;

import com.sakhbord.bord.models.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Boolean existsAdminByEmail(String email);

    Optional<Admin> findAdminByEmail(String email);

    Optional<Admin> findAdminByToken (String token);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a SET a.token = :token, a.password = :password where a.email = :email")
    void updateAdminPasswordTokenByEmail(@Param("token") String token, @Param("password") String password, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a SET a.token = :token where a.email = :email")
    void updateAdminTokenByEmail(@Param("token") String token, @Param("email") String email);


}