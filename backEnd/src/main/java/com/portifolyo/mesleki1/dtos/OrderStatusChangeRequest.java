package com.portifolyo.mesleki1.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.mesleki1.enums.OrderStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)

public record OrderStatusChangeRequest(
        String orderId,
        OrderStatus orderStatus
) {
}





