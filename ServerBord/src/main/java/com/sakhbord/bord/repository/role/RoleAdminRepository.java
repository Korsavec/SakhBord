package com.sakhbord.bord.repository.role;

import com.sakhbord.bord.enam.RoleEnum;
import com.sakhbord.bord.models.role.RoleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAdminRepository extends JpaRepository<RoleAdmin, Long> {

    RoleAdmin findRoleAdminByRoleEnum(RoleEnum roleEnum);

}