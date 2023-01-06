package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.CityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdressDtoMapper {

    private final CityRepository cityRepository;

    public AdressDtoMapper(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Adress toEntity(AdressDto dto) {
        Optional<City> o = this.cityRepository.findById(dto.cityId() );
        Adress adress = new Adress();
        if (o.isPresent()) {
            adress.setCity(o.get());
            adress.setAdress(dto.adress());
            adress.setActive(true);
            adress.setDeleted(false);
            return adress;
        } else throw new NotFoundException();
    }

    public AdressDto toDto(Adress adress) {
        AdressDto adressDto = new AdressDto(adress.getCity().getCode(),adress.getAdress());
        return adressDto;


    }
}
