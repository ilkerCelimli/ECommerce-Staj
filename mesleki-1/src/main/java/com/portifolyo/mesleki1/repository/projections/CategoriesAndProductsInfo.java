package com.portifolyo.mesleki1.repository.projections;

import com.portifolyo.mesleki1.entity.Shopper;

import java.math.BigDecimal;
import java.util.List;

public interface CategoriesAndProductsInfo {
    List<ProductInfo> getProducts();

    interface ProductInfo {
        String getId();

        boolean isIsActive();

        String getName();

        String getDescription();

        BigDecimal getPrice();

        Shopper getShopper();
    }
}
