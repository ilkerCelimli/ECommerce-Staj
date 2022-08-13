package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.CityRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.CityInfo;
import com.portifolyo.mesleki1.services.CityServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CityServicesImpl implements CityServices {

    private final CityRepository cityRepository;

    public CityServicesImpl(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }


    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public City findByName(String name) {
        if (checkCityNameIsExists(name)) return this.cityRepository.findCityByNameEquals(name).get();
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
        return this.cityRepository.existsCityByNameEquals(name);
    }

    @Override
    public City findById(int id) {
        Optional<City> o = this.cityRepository.findById(id);
        if(o.isPresent()) return o.get();
        else throw new NotFoundException();
    }

    @Override
    public List<CityInfo> findCities() {
        return this.cityRepository.findCities();
    }
}
