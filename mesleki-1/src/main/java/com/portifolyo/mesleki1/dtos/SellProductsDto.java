package com.portifolyo.mesleki1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellProductsDto {

    private String productId;
    private String shopperId;
    private String userId;
    private AdressDto adressDto;
}
