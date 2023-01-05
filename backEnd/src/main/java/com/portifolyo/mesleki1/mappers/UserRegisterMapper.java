package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {



    public User toEntity(UserRegisterDto dto) {
        Role role;

         if (dto.getRole().equals("SHOP")) {
            role = Role.SHOP;
        }
        else role = Role.USER;

        return new User(dto.getName(),dto.getSurname(),role,dto.getPhoneNumber(),dto.getEmail(),dto.getPassword(),dto.getBirtday(),dto.getTcNo(),null);
    }

}
