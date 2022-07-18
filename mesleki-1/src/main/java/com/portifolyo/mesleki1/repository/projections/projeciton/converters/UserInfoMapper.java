package com.portifolyo.mesleki1.repository.projections.projeciton.converters;

import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.mappers.AdressDtoMapper;
import com.portifolyo.mesleki1.repository.projections.projeciton.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    private final AdressDtoMapper adressDtoMapper;

    public UserInfoMapper(AdressDtoMapper adressDtoMapper) {
        this.adressDtoMapper = adressDtoMapper;
    }

    public UserInfo userInfo(User e) {
        return new UserInfo(e.getId(),e.getName(),e.getSurname(),e.getRole(),
                e.getPhoneNumber(),e.getEmail(),e.getTcNo(),null);
    }

}
