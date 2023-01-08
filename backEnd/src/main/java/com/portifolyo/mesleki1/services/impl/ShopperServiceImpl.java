package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.DataIsExistsException;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AddProductMapper;
import com.portifolyo.mesleki1.repository.ShopperRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.ShopperInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.converters.ShopperInfoMapper;
import com.portifolyo.mesleki1.repository.redisRepo.ProductRedisRepo;
import com.portifolyo.mesleki1.services.CategoriesService;
import com.portifolyo.mesleki1.services.ProductService;
import com.portifolyo.mesleki1.services.ShopperService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class ShopperServiceImpl extends BaseServicesImpl<Shopper> implements ShopperService {

    private final AddProductMapper addProductMapper;
    private final ShopperRepository shopperRepository;
    private final CategoriesService categoriesService;
    private final ProductService productService;
    private final ShopperInfoMapper shopperInfoMapper;
    private final ProductRedisRepo productRedisRepo;

    public ShopperServiceImpl(AddProductMapper addProductMapper, ShopperRepository shopperRepository, CategoriesService categoriesService, ProductService productService, ShopperInfoMapper shopperInfoMapper, ProductRedisRepo productRedisRepo) {
        super(shopperRepository);
        this.addProductMapper = addProductMapper;
        this.shopperRepository = shopperRepository;
        this.categoriesService = categoriesService;
        this.productService = productService;
        this.shopperInfoMapper = shopperInfoMapper;
        this.productRedisRepo = productRedisRepo;
    }


    @Override
    @Transactional
    public boolean shopperCheckandSave(User user) throws SqlExceptionCustom {
        Shopper shopper = new Shopper();
        shopper.setActive(false);
        shopper.setUser(user);
        save(shopper);
        return true;
    }

    @Override
    @Transactional
    public void addProduct(AddProductDto dto) throws SQLException {

        if (!checkProductIsExist(dto.productName(),dto.shopperId())) {
            Categories categories = categoriesService.findById(dto.categoriesId());
            Shopper shopper = findById(dto.shopperId());
            Product product = addProductMapper.toEntity(dto);
            product.setShopper(shopper);
            product.setCategories(categories);
          Product p =  productService.save(product);
          productRedisRepo.save(p);
        } else throw new DataIsExistsException();
    }

    @Override
    public boolean checkProductIsExist(String name,String id) {
        return this.productService.checkProductsForShopper(name,id);
    }

    @Override
    @Transactional
    public void updateProduct(AddProductDto dto,String productId) throws SQLException {
        Product p = this.productService.findProductForShopper(productId, dto.shopperId());
        if (Objects.nonNull(dto.productName()) || !Strings.isBlank(dto.productName()) || Strings.isEmpty(dto.productName())) {
            p.setName(dto.productName());
        }
        if (Objects.nonNull(dto.description()) || !Strings.isEmpty(dto.description()) || !Strings.isBlank(dto.description())) {
            p.setDescription(dto.description());
        }

        if (Objects.nonNull(dto.price())) {
            p.setPrice(dto.price());
        }
        this.productService.update(p);

    }

    @Override
    public List<ShopperInfo> findShoppers() {
        List<Shopper> list = findAll();
        List<ShopperInfo> shopper = new ArrayList<>();
        list.forEach(i -> {
            List<ProductInfo> l = productService.productInfo(i.getId());
            shopper.add(shopperInfoMapper.toDto(i,l));
        });
        return shopper;
    }

    @Override
    public ShopperInfo findShopper(String id) {
        Optional<Shopper> s = this.shopperRepository.findById(id);
        List<ProductInfo> list = productService.productInfo(id);
        if(s.isPresent()) {
            return shopperInfoMapper.toDto(s.get(),list);
        }
        else throw new NotFoundException();
    }

    @Override
    @Transactional
    public void deleteProduct(String id) throws SQLException {
        this.productService.delete(id);

    }

    @Override
    //TODO Adres güncelleme düzenlenicek
    public void updateShopper(ShopperUpdateDto dto) {
        Optional<Shopper> o = this.shopperRepository.findByUser_IdEquals(dto.userId());
        o.ifPresentOrElse(i -> {

            if (Objects.nonNull(dto.name()) || !Strings.isEmpty(dto.name()) || !Strings.isBlank(dto.name())) {
                i.setName(dto.name());
            }

            if (dto.taxNumber() != 0L) {
                i.setTaxNumber(dto.taxNumber());
            }
            try {
                update(i);
            } catch (SqlExceptionCustom e) {
                e.printStackTrace();
            }
        }, NotFoundException::new);

    }



}
