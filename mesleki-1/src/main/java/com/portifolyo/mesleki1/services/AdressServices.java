package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.Adress;

import java.util.List;

public interface AdressServices extends BaseServices<Adress> {

    List<Adress> findAdress(String userId);

}
