package com.portifolyo.mesleki1.repository.projections;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.enums.ROLE;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ShopperInfo {
    String getId();

    boolean isIsActive();

    boolean isIsDeleted();

    String getName();

    UserInfo getUser();

    AdressInfo getAdress();

    List<ProductInfo> getProductList();

    interface UserInfo {
        String getId();

        String getName();

        String getSurname();

        ROLE getRole();

        String getPhoneNumber();

        String getEmail();
    }

    interface AdressInfo {
        String getId();

        Date getCreatedAt();

        Date getUpdatedAt();

        boolean isIsActive();

        boolean isIsDeleted();

        City getCity();

        String getSokak();

        String getMahalle();

        String getIlce();

        String getBinaNo();
    }

    interface ProductInfo {
        String getId();

        String getName();

        String getDescription();

        BigDecimal getPrice();

        Categories getCategories();

        Shopper getShopper();
    }
}
