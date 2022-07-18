package com.portifolyo.mesleki1.dtos;

import lombok.Data;


public record ProductAndShopperRequest(
        String productId,
        String ShopperId
) {

}
