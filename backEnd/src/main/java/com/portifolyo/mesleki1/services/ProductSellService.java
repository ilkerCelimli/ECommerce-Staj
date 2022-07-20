package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.OrdersDto;
import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.entity.Orders;
import com.portifolyo.mesleki1.enums.OrderStatus;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductSellService extends BaseServices<Orders> {
    boolean sellProducts(SellProductsDto dtos) throws SqlExceptionCustom;
    CampaignInfo checkCampaign(String product);
    void ChangeOrderStatus(String id, @RequestBody OrderStatus orderStatus) throws SqlExceptionCustom;
    void sellProductList(List<SellProductsDto> dto) throws SqlExceptionCustom;
    Orders orderChange(SellProductsDto dto);
    List<OrdersDto> findOrders();
}
