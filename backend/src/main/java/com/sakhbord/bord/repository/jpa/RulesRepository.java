package com.sakhbord.bord.repository.jpa;

import com.sakhbord.bord.models.rules.Rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RulesRepository extends JpaRepository<Rules, Long> {
}