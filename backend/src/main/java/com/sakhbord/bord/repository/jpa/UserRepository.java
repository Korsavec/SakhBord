package com.sakhbord.bord.repository.jpa;

import com.sakhbord.bord.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Boolean existsUserByEmail(String email);

  Optional<User> findUserByEmail(String email);

  Optional<User> findUserByToken (String token);



  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.token = :token, u.password = :password where u.email = :email")
  void updateUserPasswordTokenByEmail(@Param("token") String token, @Param("password") String password, @Param("email") String email);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.token = :token where u.email = :email")
  void updateUserTokenByEmail(@Param("token") String token, @Param("email") String email);


}
