package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.City;

import java.util.List;

public interface AdressServices extends BaseServices<Adress> {

    List<Adress> findAdress(String userId);
    Adress findAdressByTitleAndUserId(String title,String userId);
    City findByCityId(String id);

}
