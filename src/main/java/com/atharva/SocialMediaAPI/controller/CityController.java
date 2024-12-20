package com.atharva.SocialMediaAPI.controller;

import com.atharva.SocialMediaAPI.model.City;
import com.atharva.SocialMediaAPI.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("/")
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @GetMapping("/")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable Long id) {
        cityService.deleteCityById(id);
    }

}
