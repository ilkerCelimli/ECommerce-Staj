package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.dtos.ShopperUpdateDto;
import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.entity.Product;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.DataIsExistsException;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AddProductMapper;
import com.portifolyo.mesleki1.repository.ShopperRepository;
import com.portifolyo.mesleki1.repository.projections.ShopperInfo;
import com.portifolyo.mesleki1.services.CategoriesService;
import com.portifolyo.mesleki1.services.ProductService;
import com.portifolyo.mesleki1.services.ShopperService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShopperServiceImpl extends BaseServicesImpl<Shopper> implements ShopperService {

    private final AddProductMapper addProductMapper;
    private final ShopperRepository shopperRepository;
    private final CategoriesService categoriesService;
    private final ProductService productService;

    public ShopperServiceImpl(AddProductMapper addProductMapper, ShopperRepository shopperRepository, CategoriesService categoriesService, ProductService productService) {
        super(shopperRepository);
        this.addProductMapper = addProductMapper;
        this.shopperRepository = shopperRepository;
        this.categoriesService = categoriesService;
        this.productService = productService;
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
    public boolean addProduct(AddProductDto dto) throws SqlExceptionCustom {

        if (checkProductIsExist(dto.getProductName())) {
            Categories categories = categoriesService.findById(dto.getCategoriesId());
            Shopper shopper = findById(dto.getShopperId());
            Product product = addProductMapper.toEntity(dto);
            product.setShopper(shopper);
            product.setCategories(categories);
            save(shopper);
            return true;
        } else throw new DataIsExistsException();
    }

    @Override
    public boolean checkProductIsExist(String name) {
        return this.shopperRepository.existsByProductList_NameEquals(name);
    }

    @Override
    public boolean updateProduct(AddProductDto dto) throws SQLException {
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
        return true;
    }

    @Override
    public List<ShopperInfo> findShoppers() {
        return this.shopperRepository.findByIsActiveAndIsDeleted(true, false);
    }

    @Override
    public ShopperInfo findShopper(String id) {
        Optional<ShopperInfo> o = this.shopperRepository.findShopperByIdEquals(id);

        o.orElseThrow(() -> new NotFoundException());
        return o.get();
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException {
        this.productService.delete(id);
        return true;
    }

    @Override
    //TODO Adres güncelleme düzenlenicek
    public boolean updateShopper(ShopperUpdateDto dto) {
        Optional<Shopper> o = this.shopperRepository.findByUser_IdEquals(dto.getUserId());
        o.ifPresentOrElse(i -> {

            if (Objects.nonNull(dto.getName()) || !Strings.isEmpty(dto.getName()) || !Strings.isBlank(dto.getName())) {
                i.setName(dto.getName());
            }
            if (Objects.nonNull(dto.getAdressDto())) {

            }
            if (Objects.nonNull(dto.getTaxNumber()) || dto.getTaxNumber() != 0L) {
                i.setTaxNumber(dto.getTaxNumber());
            }
            try {
                update(i);
            } catch (SqlExceptionCustom e) {
                e.printStackTrace();
            }
        }, () -> new NotFoundException());
        return true;
    }




}
