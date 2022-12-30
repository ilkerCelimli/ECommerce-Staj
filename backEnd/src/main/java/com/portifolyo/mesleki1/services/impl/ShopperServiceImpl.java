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
import com.portifolyo.mesleki1.services.CategoriesService;
import com.portifolyo.mesleki1.services.ProductService;
import com.portifolyo.mesleki1.services.ShopperService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class ShopperServiceImpl extends BaseServicesImpl<Shopper> implements ShopperService {

    private final AddProductMapper addProductMapper;
    private final ShopperRepository shopperRepository;
    private final CategoriesService categoriesService;
    private final ProductService productService;
    private final ShopperInfoMapper shopperInfoMapper;

    public ShopperServiceImpl(AddProductMapper addProductMapper, ShopperRepository shopperRepository, CategoriesService categoriesService, ProductService productService, ShopperInfoMapper shopperInfoMapper) {
        super(shopperRepository);
        this.addProductMapper = addProductMapper;
        this.shopperRepository = shopperRepository;
        this.categoriesService = categoriesService;
        this.productService = productService;
        this.shopperInfoMapper = shopperInfoMapper;
    }


    @Override
    public boolean shopperCheckandSave(User user) throws SqlExceptionCustom {
        Shopper shopper = new Shopper();
        shopper.setActive(false);
        shopper.setUser(user);
        save(shopper);
        return true;
    }

    @Override
    public void addProduct(AddProductDto dto) throws SQLException {

        if (!checkProductIsExist(dto.getProductName(),dto.getShopperId())) {
            Categories categories = categoriesService.findById(dto.getCategoriesId());
            Shopper shopper = findById(dto.getShopperId());
            Product product = addProductMapper.toEntity(dto);
            product.setShopper(shopper);
            product.setCategories(categories);
            productService.save(product);
        } else throw new DataIsExistsException();
    }

    @Override
    public boolean checkProductIsExist(String name,String id) {
        return this.productService.checkProductsForShopper(name,id);
    }

    @Override
    public void updateProduct(AddProductDto dto) throws SQLException {
        Product p = this.productService.findProductForShopper(dto.getProductName(), dto.getShopperId());
        if (Objects.nonNull(dto.getProductName()) || !Strings.isBlank(dto.getProductName()) || Strings.isEmpty(dto.getProductName())) {
            p.setName(dto.getProductName());
        }
        if (Objects.nonNull(dto.getDescription()) || !Strings.isEmpty(dto.getDescription()) || !Strings.isBlank(dto.getDescription())) {
            p.setDescription(dto.getDescription());
        }

        if (Objects.nonNull(dto.getPrice())) {
            p.setPrice(dto.getPrice());
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
    public void deleteProduct(String id) throws SQLException {
        this.productService.delete(id);

    }

    @Override
    //TODO Adres güncelleme düzenlenicek
    public void updateShopper(ShopperUpdateDto dto) {
        Optional<Shopper> o = this.shopperRepository.findByUser_IdEquals(dto.getUserId());
        o.ifPresentOrElse(i -> {

            if (Objects.nonNull(dto.getName()) || !Strings.isEmpty(dto.getName()) || !Strings.isBlank(dto.getName())) {
                i.setName(dto.getName());
            }

            if (dto.getTaxNumber() != 0L) {
                i.setTaxNumber(dto.getTaxNumber());
            }
            try {
                update(i);
            } catch (SqlExceptionCustom e) {
                e.printStackTrace();
            }
        }, NotFoundException::new);

    }



}
