package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.ShopperUpdateMapper;
import com.portifolyo.mesleki1.repository.ProductRepository;
import com.portifolyo.mesleki1.services.ProductService;
import com.portifolyo.mesleki1.services.UserServices;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl extends BaseServicesImpl<Product> implements ProductService {
    private final UserServices userServices;
    private final ShopperUpdateMapper shopperUpdateMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(UserServices userServices, ShopperUpdateMapper shopperUpdateMapper, ProductRepository productRepository) {
        super(productRepository);
        this.userServices = userServices;
        this.shopperUpdateMapper = shopperUpdateMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Product findProductForShopper(String productId, String shopperId) {
        Optional<Product> o = this.productRepository.findByNameEqualsAndShopper_IdEquals(productId, shopperId);
        o.orElseThrow(() -> new NotFoundException());
        return o.get();
    }



    }
