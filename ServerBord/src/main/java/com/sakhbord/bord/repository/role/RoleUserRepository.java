package com.sakhbord.bord.repository.role;

import com.sakhbord.bord.enam.RoleEnum;
import com.sakhbord.bord.models.role.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {


  RoleUser findRoleUserByRoleEnum(RoleEnum roleEnum);
}
