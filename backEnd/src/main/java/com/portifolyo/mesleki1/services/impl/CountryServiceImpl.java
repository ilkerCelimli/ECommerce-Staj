package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.Country;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.CountryRepository;
import com.portifolyo.mesleki1.services.CountryServices;

public class CountryServiceImpl extends BaseServicesImpl<Country> implements CountryServices {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        super(countryRepository);
        this.countryRepository = countryRepository;
    }

    public boolean countryCheck(String country) {
        boolean result = this.countryRepository.findCountryByCountryEquals(country).isPresent();
        return result;
    }

    public boolean countrySave(Country country) throws SqlExceptionCustom {
        save(country);
        return true;
    }


}
