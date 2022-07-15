package com.portifolyo.mesleki1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCampaignDto {

    private String shopperId;
    private String productId;
    private BigDecimal discountRate;
    private Date startDate;
    private Date endDate;
    private String description;

}
