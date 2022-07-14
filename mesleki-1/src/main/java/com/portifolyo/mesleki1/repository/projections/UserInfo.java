package com.portifolyo.mesleki1.repository.projections;

import com.portifolyo.mesleki1.entity.City;
import com.portifolyo.mesleki1.enums.ROLE;

import java.util.List;

public interface UserInfo {
    String getId();

    boolean isIsActive();

    boolean isIsDeleted();

    String getName();

    String getSurname();

    ROLE getRole();

    String getPhoneNumber();

    String getEmail();

    List<AdressInfo> getAdressList();

    interface AdressInfo {
        String getId();

        City getCity();

        String getSokak();

        String getMahalle();

        String getIlce();

        String getBinaNo();
    }
}
