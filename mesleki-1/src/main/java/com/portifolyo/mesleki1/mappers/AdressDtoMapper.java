package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.entity.AdresEntities.Adress;
import com.portifolyo.mesleki1.entity.AdresEntities.City;
import com.portifolyo.mesleki1.entity.AdresEntities.Country;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.CityRepository;
import com.portifolyo.mesleki1.repository.CountryRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class AdressDtoMapper {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    public AdressDtoMapper(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public Adress toEntity(AdressDto dto) {
        Optional<City> o = this.cityRepository.findById(dto.getCityId());
        if (o.isPresent()) {
            Adress adres = new Adress();
            adres.setCity(o.get());
            adres.setBinaNo(dto.getBinaNo());
            adres.setIlce(dto.getIlce());
            adres.setMahalle(dto.getMahalle());
            adres.setSokak(dto.getSokak());
            return adres;
        }
            else throw new NotFoundException();
    }


}
