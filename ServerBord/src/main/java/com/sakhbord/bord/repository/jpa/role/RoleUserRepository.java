package com.sakhbord.bord.repository.jpa.role;

import com.sakhbord.bord.models.role.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {


  RoleUser findRoleUserByName(String value);
}
