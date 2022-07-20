package com.portifolyo.mesleki1.dtos;

import com.portifolyo.mesleki1.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangeRequest {

    private String orderId;
    private OrderStatus orderStatus;

}
