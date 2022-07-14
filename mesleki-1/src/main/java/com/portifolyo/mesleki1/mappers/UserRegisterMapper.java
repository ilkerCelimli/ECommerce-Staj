package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {



    public User toEntity(UserRegisterDto dto) {

        return new User(dto.getName(),dto.getSurname(),dto.getRole(),dto.getPhoneNumber(),dto.getEmail(),dto.getPassword(),dto.getBirtday(),dto.getTcNo(),null);
    }

}
