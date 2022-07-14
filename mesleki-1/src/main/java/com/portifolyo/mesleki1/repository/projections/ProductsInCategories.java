package com.portifolyo.mesleki1.repository.projections;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsInCategories {
    String getId();

    boolean isIsActive();

    boolean isIsDeleted();

    String getName();

    String getDescription();

    List<ProductInfo> getProducts();

    interface ProductInfo {
        boolean isIsActive();

        boolean isIsDeleted();

        String getName();

        String getDescription();

        BigDecimal getPrice();
    }
}
