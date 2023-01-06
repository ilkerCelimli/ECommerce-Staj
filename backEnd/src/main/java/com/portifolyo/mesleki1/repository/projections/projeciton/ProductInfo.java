package com.portifolyo.mesleki1.repository.projections.projeciton;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public record ProductInfo(
        String id,
        String name,
        String description,
        BigDecimal price,
        byte[] image,
        @JsonIgnore
        CategoriesInfo categories
) {
}
