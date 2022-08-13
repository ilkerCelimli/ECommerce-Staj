package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.repository.projections.projeciton.CityInfo;

import java.util.List;

public interface CityServices {

    City findByName(String name);
    boolean checkCityCodeIsExists(String code);
    boolean checkCityNameIsExists(String name);
    City findById(int id);
    List<CityInfo> findCities();


}
