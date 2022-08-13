package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.AdressRepository;
import com.portifolyo.mesleki1.services.AdressServices;
import com.portifolyo.mesleki1.services.CityServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdressServicesImpl extends BaseServicesImpl<Adress> implements AdressServices {

    private final AdressRepository adressRepository;
    private final CityServices cityServices;

    public AdressServicesImpl(AdressRepository adressRepository, CityServices cityServices) {
        super(adressRepository);
        this.adressRepository = adressRepository;
        this.cityServices = cityServices;
    }


    @Override
    public List<Adress> findAdress(String userId) {
        return this.adressRepository.findAdressByUserId(userId);
    }

    @Override
    public Adress findAdressByTitleAndUserId(String userId) {
        Optional<Adress> o = this.adressRepository.findAdressbyTitleAndUserId(userId);
        return o.orElseThrow(NotFoundException::new);
    }

    @Override
    public City findByCityId(int id) {
        return cityServices.findById(id);
    }
}
