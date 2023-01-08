package com.portifolyo.mesleki1.repository.projections.projeciton;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ShopperInfo(

        String id,
        String name,
        long taxNumber,
        List<ProductInfo> product,
        UserInfo userInfo


) {
}
