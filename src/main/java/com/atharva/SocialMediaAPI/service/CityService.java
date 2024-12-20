package com.atharva.SocialMediaAPI.service;

import com.atharva.SocialMediaAPI.model.City;
import com.atharva.SocialMediaAPI.repository.CityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityService {

    private final CityRepo cityRepo;

    @Autowired
    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }


    public City createCity(City city) {
        city = cityRepo.save(city);
        if (city == null) {
            log.error("Failed to save city");
            return null;
        }
        log.info("Saved city: {}" ,city);
        return city;
    }

    public List<City> getAllCities() {
        List<City> cityList =  cityRepo.findAll();
        if (cityList.isEmpty()) {
            log.error("Failed to get all cities");
            return null;
        }

        log.info("Found {} cities", cityList.toString());
        return cityList;
    }

    public City getCityById(Long id) {
        City city = cityRepo.findById(id).orElse(null);

        if (city == null) {
            log.error("Failed to get city by id");
            return null;
        }

        log.info("Get city by id: {}", city);
        return city;
    }

    public void deleteCityById(Long id) {
        try {
            cityRepo.deleteById(id);
            log.info("Deleted city with id {}", id);
        }catch (Exception e) {
            log.error("Failed to delete city by id");
        }
    }
}
