package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.CityRepository;
import com.portifolyo.mesleki1.repository.CountryRepository;
import org.springframework.stereotype.Component;

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
        Adress adres = new Adress();
        adres.setBinaNo(dto.getBinaNo());
        adres.setIlce(dto.getIlce());
        adres.setMahalle(dto.getMahalle());
        adres.setSokak(dto.getSokak());
        return adres;
    }


}
