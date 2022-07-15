package com.portifolyo.mesleki1.api;

import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.ShopperInfo;
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


    @GetMapping("/findShoppers")
    public ResponseEntity<List<ShopperInfo>> findAllShoppers() {
        return ResponseEntity.ok().body(this.shopperService.findShoppers());
    }

    @GetMapping("/findShoppersProducts/{id}")
    public ResponseEntity<ShopperInfo> findShoppersProducts(@PathVariable String id) {
        ShopperInfo s = this.shopperService.findShopper(id);
        return ResponseEntity.ok().body(s);
    }

    @PostMapping("/addProduct/{id}")
    public ResponseEntity<ShopperInfo> addProduct(@RequestBody AddProductDto dto) throws SqlExceptionCustom {
        this.shopperService.addProduct(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ShopperInfo> updateProduct(@RequestBody AddProductDto dto) throws SQLException {
        this.shopperService.updateProduct(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteProduct")
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


}
