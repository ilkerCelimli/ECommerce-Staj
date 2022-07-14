package com.portifolyo.mesleki1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressDto {


    private String CountryId;
    private String cityId;
    private String cityName;
    private String mahalle;
    private String sokak;
    private String ilce;
    private String binaNo;

}
