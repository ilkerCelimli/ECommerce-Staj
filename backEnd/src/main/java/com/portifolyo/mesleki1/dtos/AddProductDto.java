package com.portifolyo.mesleki1.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddProductDto(
     String shopperId,
     String categoriesId,
     String productName,
     String description,
     BigDecimal price,
     byte[] image
){
}
