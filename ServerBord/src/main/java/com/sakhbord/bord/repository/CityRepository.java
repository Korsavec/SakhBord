package com.sakhbord.bord.repository;

import com.sakhbord.bord.models.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    Optional<City> findCityByName(String name);


}