package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.ShopperRepository;
import com.portifolyo.mesleki1.services.ShopperService;
import org.springframework.stereotype.Service;

@Service
public class ShopperServiceImpl extends BaseServicesImpl<Shopper> implements ShopperService {


    private final ShopperRepository shopperRepository;

    public ShopperServiceImpl(ShopperRepository shopperRepository) {
        super(shopperRepository);
        this.shopperRepository = shopperRepository;
    }


    @Override
    public boolean shopperCheckandSave(User user) throws SqlExceptionCustom {
        Shopper shopper = new Shopper();
        shopper.setActive(false);
        shopper.setUser(user);
        save(shopper);
        return true;
    }
}
