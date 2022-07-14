package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.AdresEntities.City;

import java.util.Optional;

public interface CityRepository extends BaseRepository<City> {

    boolean existsCityByCityEquals(String city);
    boolean existsCityByCodeEquals(String code);
    Optional<City> findCityByCityEquals(String city);
    Optional<City> findCityByCodeEquals(String code);
}
