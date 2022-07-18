package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.ProductRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.converters.ProductInfoMapper;
import com.portifolyo.mesleki1.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseServicesImpl<Product> implements ProductService {


    private final ProductRepository productRepository;
    private final ProductInfoMapper productInfoMapper;
    public ProductServiceImpl(ProductRepository productRepository, ProductInfoMapper productInfoMapper) {
        super(productRepository);

        this.productRepository = productRepository;
        this.productInfoMapper = productInfoMapper;
    }

    @Override
    public Product findProductForShopper(String productId, String shopperId) {
        Optional<Product> o = this.productRepository.findByProductByProductIdAndShopperId(productId,shopperId);
        o.orElseThrow(NotFoundException::new);
        return o.get();
    }

    public List<ProductInfo> productInfo(String shopperId) {
       List<Product> list = this.productRepository.findProductInfo(shopperId);
       List<ProductInfo> infoList = new ArrayList<>();
       list.stream().forEach(i -> infoList.add(productInfoMapper.productInfo(i)));
       return infoList;
    }

    @Override
    public boolean checkProductsForShopper(String name, String id) {
        return this.productRepository.checkProductsForShoppers(name,id);
    }

    @Override
    public List<ProductInfo> findProductsForCategories(String id) {
        return this.productInfoMapper.toDtoOnList(this.productRepository.findProductsByCategories(id));
    }




}
