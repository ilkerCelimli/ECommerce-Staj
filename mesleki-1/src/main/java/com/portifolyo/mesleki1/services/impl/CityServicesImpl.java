package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.CityRepository;
import com.portifolyo.mesleki1.services.CityServices;
import org.springframework.stereotype.Service;

@Service

public class CityServicesImpl extends BaseServicesImpl<City> implements CityServices {

    private final CityRepository cityRepository;

    public CityServicesImpl(CityRepository cityRepository) {
        super(cityRepository);
        this.cityRepository = cityRepository;
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public City findByName(String name) {
        if (checkCityNameIsExists(name)) return this.cityRepository.findCityByCityEquals(name).get();
        if (checkCityCodeIsExists(name)) {
            return this.cityRepository.findCityByCodeEquals(name).get();
        }
        throw new NotFoundException();

    }

    @Override
    public boolean checkCityCodeIsExists(String code) {
        return this.cityRepository.existsCityByCodeEquals(code);

    }

    @Override
    public boolean checkCityNameIsExists(String name) {
        return this.cityRepository.existsCityByCityEquals(name);
    }
}
