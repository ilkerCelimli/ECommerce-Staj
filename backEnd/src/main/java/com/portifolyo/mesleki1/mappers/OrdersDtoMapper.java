package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.OrdersDto;
import com.portifolyo.mesleki1.dtos.UserInfo;
import com.portifolyo.mesleki1.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrdersDtoMapper {

    private final AdressDtoMapper adressDtoMapper;


  public OrdersDto toDto(Orders orders) {
       return new OrdersDto(orders.getId(),orders.getPrice(),adressDtoMapper.toDto(orders.getAdress()),orders.getOrderStatus(),new UserInfo(orders.getPerson().getId(),orders.getProduct().getId(),orders.getProduct().getId()));

   }

}
