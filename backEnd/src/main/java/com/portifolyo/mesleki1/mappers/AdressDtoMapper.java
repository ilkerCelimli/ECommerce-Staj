package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.City;
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
        Optional<City> o = this.cityRepository.findById(dto.getCityId());
        Adress adres = new Adress();
        if (o.isPresent()) {
            adres.setCity(o.get());
        } else {
            adres.setTitle(dto.getTitle());
        }
        adres.setBinaNo(dto.getBinaNo());
        adres.setIlce(dto.getIlce());
        adres.setMahalle(dto.getMahalle());
        adres.setSokak(dto.getSokak());
        return adres;

    }

    public AdressDto toDto(Adress adress) {
        AdressDto dto = new AdressDto();

        dto.setBinaNo(adress.getBinaNo());
        dto.setMahalle(adress.getMahalle());
        dto.setIlce(adress.getIlce());
        dto.setSokak(adress.getSokak());
        dto.setCityId(adress.getCity().getId());
        dto.setCityName(adress.getCity().getCity());
        dto.setCountryId(adress.getCity().getCountry().getId());
        return dto;

    }


}
