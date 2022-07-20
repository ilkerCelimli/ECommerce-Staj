package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Adress;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.services.UserServices;
import org.springframework.stereotype.Component;

@Component
public class ShopperUpdateMapper {

    private final UserServices userServices;
    private final AdressDtoMapper adressDtoMapper;

    public ShopperUpdateMapper(UserServices userServices, AdressDtoMapper adressDtoMapper) {
        this.userServices = userServices;
        this.adressDtoMapper = adressDtoMapper;
    }

    public Shopper toEntity(ShopperUpdateDto dto) {
        User u = userServices.findById(dto.getUserId());
        Adress adress = adressDtoMapper.toEntity(dto.getAdressDto());
        Shopper s = new Shopper();

        s.setUser(u);
       // s.getUser().setAdressList(adress);
        s.setActive(false);
        s.setName(dto.getName());
        s.setTaxNumber(dto.getTaxNumber());
        return s;
    }

}
