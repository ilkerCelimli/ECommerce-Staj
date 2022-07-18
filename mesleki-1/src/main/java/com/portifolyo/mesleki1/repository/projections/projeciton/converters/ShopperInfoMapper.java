package com.portifolyo.mesleki1.repository.projections.projeciton.converters;

import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.ShopperInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopperInfoMapper {

    private final UserInfoMapper userInfoMapper;

    public ShopperInfoMapper( UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public ShopperInfo toDto(Shopper shopper, List<ProductInfo> shopperInfos){
        return new ShopperInfo(shopper.getId(),shopper.getName(), shopper.getTaxNumber(),shopperInfos,userInfoMapper.userInfo(shopper.getUser()));
    }

}
