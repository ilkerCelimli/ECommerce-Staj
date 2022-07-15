package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.ShopperInfo;

import java.sql.SQLException;
import java.util.List;

public interface ShopperService extends BaseServices<Shopper>  {

    boolean shopperCheckandSave(User user) throws SqlExceptionCustom;
    boolean addProduct(AddProductDto dto) throws SqlExceptionCustom;
    boolean checkProductIsExist(String name);
    boolean updateProduct(AddProductDto dto) throws SQLException;
    List<ShopperInfo> findShoppers();
    ShopperInfo findShopper(String id);
    boolean deleteProduct(String id) throws SQLException ;
    boolean updateShopper(ShopperUpdateDto dto);
}
