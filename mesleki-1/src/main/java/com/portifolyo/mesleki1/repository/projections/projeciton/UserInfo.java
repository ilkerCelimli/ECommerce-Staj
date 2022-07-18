package com.portifolyo.mesleki1.repository.projections.projeciton;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.enums.ROLE;

import java.util.List;

public record UserInfo(
        String id,
        String name,
        String surname,
        ROLE role,
        String phoneNumber,
        String email,
        long TcNo,
        List<AdressDto> adressDto
) {


}
