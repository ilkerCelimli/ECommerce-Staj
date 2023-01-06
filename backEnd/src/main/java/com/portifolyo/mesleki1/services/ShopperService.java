package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.ShopperInfo;

import java.sql.SQLException;
import java.util.List;

public interface ShopperService extends BaseServices<Shopper>  {

    boolean shopperCheckandSave(User user) throws SqlExceptionCustom;
    void addProduct(AddProductDto dto) throws SQLException;
    boolean checkProductIsExist(String name,String id);
    void updateProduct(AddProductDto dto,String productId) throws SQLException;
    List<ShopperInfo> findShoppers();
    ShopperInfo findShopper(String id);
    void deleteProduct(String id) throws SQLException ;
    void updateShopper(ShopperUpdateDto dto);


}
