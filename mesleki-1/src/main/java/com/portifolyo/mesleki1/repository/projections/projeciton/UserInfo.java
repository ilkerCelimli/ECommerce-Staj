package com.portifolyo.mesleki1.repository.projections.projeciton;

import com.portifolyo.mesleki1.dtos.AdressDto;
import com.portifolyo.mesleki1.enums.ROLE;

public record UserInfo(
        String id,
        String name,
        String surname,
        ROLE role,
        String phoneNumber,
        String email,
        long TcNo,
        AdressDto adressDto
) {


}
