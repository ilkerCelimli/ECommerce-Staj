package com.portifolyo.mesleki1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {

    private String shopperId;
    private String categoriesId;
    private String productName;
    private String Description;
    private BigDecimal price;
    private byte[] image;

}
