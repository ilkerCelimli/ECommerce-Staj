package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.entity.Orders;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;

public interface ProductSellService extends BaseServices<Orders> {
    boolean sellProducts(SellProductsDto dtos) throws SqlExceptionCustom;
    Campaign checkCampaign(String product,String shopper);

}
