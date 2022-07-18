package com.portifolyo.mesleki1.repository.projections.projeciton;

import com.portifolyo.mesleki1.entity.Shopper;

import java.math.BigDecimal;
import java.util.Date;

public interface CampaignInfo {
    String getId();

    Date getCreatedAt();

    Date getUpdatedAt();

    boolean isIsActive();

    boolean isIsDeleted();

    String getCode();

    String getDescription();

    double getDiscountRate();

    Date getStartDate();

    Date getEndDate();

    ProductInfo getProduct();

    interface ProductInfo {
        String getId();

        String getName();

        String getDescription();

        BigDecimal getPrice();

        Shopper getShopper();
    }
}
