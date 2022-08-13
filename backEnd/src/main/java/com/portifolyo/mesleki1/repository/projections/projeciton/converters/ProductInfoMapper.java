package com.portifolyo.mesleki1.repository.projections.projeciton.converters;

import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductInfoMapper {

    private final CategoriesInfoMapper categoriesInfoMapper;

    public ProductInfoMapper(CategoriesInfoMapper categoriesInfoMapper) {
        this.categoriesInfoMapper = categoriesInfoMapper;
    }

    public ProductInfo productInfo(Product entity){
        return new ProductInfo(entity.getId(),entity.getName(),entity.getDescription(),entity.getPrice(),entity.getImage(),categoriesInfoMapper.toDto(entity.getCategories()));
    }

    public List<ProductInfo> toDtoOnList(List<Product> productList) {
        List<ProductInfo> p = new ArrayList<>();
        productList.forEach(i -> p.add(productInfo(i)));
        return p;

    }


}
