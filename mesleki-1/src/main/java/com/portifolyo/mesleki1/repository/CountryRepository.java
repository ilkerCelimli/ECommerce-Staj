package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.AdresEntities.Country;

import java.util.Optional;

public interface CountryRepository extends BaseRepository<Country> {

    Optional<Country> findCountryByCountryEquals(String country);

}
