package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.City;

public interface CityServices extends BaseServices<City> {

    City findByName(String name);
    boolean checkCityCodeIsExists(String code);
    boolean checkCityNameIsExists(String name);



}
