package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.UserRegisterDto;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {



    public User toEntity(UserRegisterDto dto) {
        Role role;

         if (dto.role().equals("SHOP")) {
            role = Role.SHOP;
        }
        else role = Role.USER;

        return new User(dto.name(),dto.surname(),role,dto.phoneNumber(),dto.email(),dto.password(),dto.birtday(),dto.tcNo(),null);
    }

}
