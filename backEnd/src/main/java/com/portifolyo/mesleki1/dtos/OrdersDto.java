package com.portifolyo.mesleki1.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.mesleki1.enums.OrderStatus;


import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrdersDto(
        String id,
        BigDecimal price,
        AdressDto cargoAdress,
        OrderStatus orderStatus,
        UserInfo usersInfo
) {
}

