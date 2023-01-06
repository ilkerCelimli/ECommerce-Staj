package com.portifolyo.mesleki1.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)

public record AddCampaignDto(
        String shopperId,
        String productId,
        BigDecimal discountRate,
        Date startDate,
        Date endDate,
        String description
){

}
