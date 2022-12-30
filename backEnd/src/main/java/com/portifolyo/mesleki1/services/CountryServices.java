package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.Country;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;

public interface CountryServices extends BaseServices<Country> {

    boolean countrySave(Country country) throws SqlExceptionCustom;
    boolean countryCheck(String country);
}
