package com.portifolyo.mesleki1.dtos;

import com.portifolyo.mesleki1.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public record OrdersDto(
        String id,
        BigDecimal price,
        AdressDto cargoAdress,
        OrderStatus orderStatus,
        UserInfo usersInfo
) {
}

