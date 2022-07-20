package com.portifolyo.mesleki1.repository.projections.projeciton.converters;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.mappers.AdressDtoMapper;
import com.portifolyo.mesleki1.repository.projections.projeciton.UserInfo;
import com.portifolyo.mesleki1.services.AdressServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInfoMapper {

    private final AdressDtoMapper adressDtoMapper;
    private final AdressServices adressServices;


    public UserInfo userInfo(User e) {
        List<AdressDto> list = new ArrayList<>();
        adressServices.findAdress(e.getId()).forEach(i -> list.add(adressDtoMapper.toDto(i)));
        return new UserInfo(e.getId(),e.getName(),e.getSurname(),e.getRole(),
                e.getPhoneNumber(),e.getEmail(),e.getTcNo(),list);
    }

}
