package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.AdresEntities.Country;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;

import java.util.Optional;

public interface CountryServices extends BaseServices<Country> {

    boolean countrySave(Country country) throws SqlExceptionCustom;
    boolean countryCheck(String country);
}
