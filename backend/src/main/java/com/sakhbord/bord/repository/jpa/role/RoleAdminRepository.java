package com.sakhbord.bord.repository.jpa.role;

import com.sakhbord.bord.models.role.RoleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleAdminRepository extends JpaRepository<RoleAdmin, Long> {

    RoleAdmin findRoleAdminByName(String value);

}