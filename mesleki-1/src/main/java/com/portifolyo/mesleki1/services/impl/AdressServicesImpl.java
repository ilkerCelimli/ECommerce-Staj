package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.repository.AdressRepository;
import com.portifolyo.mesleki1.services.AdressServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressServicesImpl extends BaseServicesImpl<Adress> implements AdressServices {

    private final AdressRepository adressRepository;

    public AdressServicesImpl(AdressRepository adressRepository) {
        super(adressRepository);
        this.adressRepository = adressRepository;
    }


    @Override
    public List<Adress> findAdress(String userId) {
        return this.adressRepository.findAdressByUserId(userId);
    }
}
