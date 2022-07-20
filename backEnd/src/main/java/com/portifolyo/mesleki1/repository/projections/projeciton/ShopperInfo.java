package com.portifolyo.mesleki1.repository.projections.projeciton;

import java.util.List;

public record ShopperInfo(

        String id,
        String name,
        long taxNumber,
        List<ProductInfo> product,
        UserInfo userInfo


) {
}
