package com.portifolyo.mesleki1.repository.projections.projeciton;

import java.math.BigDecimal;

public record ProductInfo(
        String id,
        String name,
        String description,
        BigDecimal price,
        CategoriesInfo categories
) {
}
