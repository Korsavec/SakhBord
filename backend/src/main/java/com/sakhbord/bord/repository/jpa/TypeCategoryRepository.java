package com.sakhbord.bord.repository.jpa;

import com.sakhbord.bord.models.type.category.TypeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface TypeCategoryRepository extends JpaRepository<TypeCategory, Long> {

    Optional<TypeCategory> findTypeCategoryByName(String name);

}