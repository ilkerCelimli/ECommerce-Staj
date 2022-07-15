package com.portifolyo.mesleki1.dtos;

import com.portifolyo.mesleki1.dtos.AdressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopperUpdateDto {

    private String userId;
    private String name;
    private long taxNumber;
    private AdressDto adressDto;


}
