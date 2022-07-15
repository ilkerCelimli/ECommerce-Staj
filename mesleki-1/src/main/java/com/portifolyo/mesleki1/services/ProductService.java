package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Product;

public interface ProductService extends BaseServices<Product> {

    Product findProductForShopper(String productId,String shopperId);

}
