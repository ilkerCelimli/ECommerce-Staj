package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;

import java.util.List;

public interface ProductService extends BaseServices<Product> {

    Product findProductForShopper(String productId,String shopperId);
    List<ProductInfo> productInfo(String shopperId);
    boolean checkProductsForShopper(String name,String id);
    List<ProductInfo> findProductsForCategories(String id);
}
