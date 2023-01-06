package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.OrderStatusChangeRequest;
import com.portifolyo.mesleki1.dtos.OrdersDto;
import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.services.ProductSellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sellProducts")

public class OrderApi {

    private final ProductSellService productSellService;

    @GetMapping("/findOrders")
    public List<OrdersDto> findOrders() {
        return this.productSellService.findOrders();
    }

    @PostMapping("/sellProduct")
    public ResponseEntity<SellProductsDto> sellProduct(@RequestBody SellProductsDto dto) throws SqlExceptionCustom {
       boolean result = this.productSellService.sellProducts(dto);
        return result ? ResponseEntity.ok().build(): ResponseEntity.badRequest().build();
    }

    @PostMapping("/SellProducts")
    public ResponseEntity<SellProductsDto> sellProducts(@RequestBody List<SellProductsDto> dtos) throws SqlExceptionCustom {

        this.productSellService.sellProductList(dtos);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orderStatusChange")
    public ResponseEntity<Object> orderStatusChange(OrderStatusChangeRequest req) throws SqlExceptionCustom {
        this.productSellService.changeOrderStatus(req.orderId(),req.orderStatus());
        return ResponseEntity.ok().build();
    }






}
