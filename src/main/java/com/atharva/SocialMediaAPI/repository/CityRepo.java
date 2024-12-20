package com.atharva.SocialMediaAPI.repository;

import com.atharva.SocialMediaAPI.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {

}
