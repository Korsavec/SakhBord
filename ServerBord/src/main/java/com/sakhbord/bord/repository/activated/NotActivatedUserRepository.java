package com.sakhbord.bord.repository.activated;

import com.sakhbord.bord.models.activation.NotActivatedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface NotActivatedUserRepository extends JpaRepository<NotActivatedUser, Long> {


}