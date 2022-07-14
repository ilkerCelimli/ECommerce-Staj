package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.Shopper;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;

public interface ShopperService extends BaseServices<Shopper>  {

    boolean shopperCheckandSave(User user) throws SqlExceptionCustom;

}
