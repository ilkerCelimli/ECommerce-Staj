package com.portifolyo.mesleki1.repository.projections.projeciton;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductInfo(
        String id,
        String name,
        String description,
        BigDecimal price,
        byte[] image
) {
}
