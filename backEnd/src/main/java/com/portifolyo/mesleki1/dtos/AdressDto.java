package com.portifolyo.mesleki1.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public record AdressDto(
    int cityId,
    String adress
){
}
