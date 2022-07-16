package com.portifolyo.mesleki1.repository.projections;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.entity.Shopper;

import java.math.BigDecimal;
import java.util.Date;

public interface CampaignInfo {
    String getId();

    String getCode();

    String getDescription();

    double getDiscountRate();

    Date getStartDate();

    Date getEndDate();

    ProductInfo getProduct();

    interface ProductInfo {
        String getName();

        String getDescription();

        BigDecimal getPrice();

        Categories getCategories();

        Shopper getShopper();
    }
}
