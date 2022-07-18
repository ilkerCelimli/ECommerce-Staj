package com.portifolyo.mesleki1.repository.projections.projeciton;

import java.util.List;

public record CategoriesApiInfo(
        String id,
        String name,
        String description,
        List<ProductInfo>categories
) {
}
