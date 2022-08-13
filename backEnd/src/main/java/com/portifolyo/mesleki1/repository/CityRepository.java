package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.repository.projections.projeciton.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Integer> {

    boolean existsCityByNameEquals(String name);
    boolean existsCityByCodeEquals(String code);
    Optional<City> findCityByNameEquals(String city);
    Optional<City> findCityByCodeEquals(String code);

    @Query("select c from City c order by c.code DESC")
    List<CityInfo> findCities();


}
