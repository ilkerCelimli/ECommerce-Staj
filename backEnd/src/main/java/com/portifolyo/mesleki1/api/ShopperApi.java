package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.dtos.SellProductsDto;
import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.enums.OrderStatus;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.ShopperInfo;
import com.portifolyo.mesleki1.services.CampaignService;
import com.portifolyo.mesleki1.services.ProductSellService;
import com.portifolyo.mesleki1.services.ShopperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/shopper")
@RestController
@RequiredArgsConstructor

public class ShopperApi {

    private final ShopperService shopperService;
    private final CampaignService campaignService;
    private final ProductSellService productSellService;

    @GetMapping("/findShoppers")
    public ResponseEntity<List<ShopperInfo>> findAllShoppers() {
        return ResponseEntity.ok(this.shopperService.findShoppers());
    }

    @GetMapping("/findShoppersProducts/{id}")
    public ResponseEntity<ShopperInfo> findShoppersProducts(@PathVariable String id) {
        ShopperInfo s = this.shopperService.findShopper(id);
        return ResponseEntity.ok().body(s);
    }

    @PostMapping("/addProduct/")
    public ResponseEntity<ShopperInfo> addProduct(@RequestBody AddProductDto dto) throws SQLException {
        this.shopperService.addProduct(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ShopperInfo> updateProduct(@RequestBody AddProductDto dto) throws SQLException {
        this.shopperService.updateProduct(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<ShopperInfo> deleteProduct(@PathVariable String id) throws SQLException {
        this.shopperService.deleteProduct(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/updateShoper")
    public ResponseEntity<ShopperInfo> updateShopper(@RequestBody ShopperUpdateDto dto) {
    this.shopperService.updateShopper(dto);
    return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteShopper/{id}")
    public ResponseEntity<ShopperInfo> deleteShopper(@PathVariable String id) throws SQLException {
        this.shopperService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addCampaign")
    public ResponseEntity<ShopperInfo> addCampaign(@RequestBody AddCampaignDto dto) throws SqlExceptionCustom {
        this.campaignService.addCampaign(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/ChangeOrderStatus/{orderId}")
    public ResponseEntity<SellProductsDto> changeOrderStatus(@PathVariable String orderId, @RequestBody OrderStatus orderStatus) throws SqlExceptionCustom {
        this.productSellService.changeOrderStatus(orderId,orderStatus);
        return ResponseEntity.ok().build();
    }

}
