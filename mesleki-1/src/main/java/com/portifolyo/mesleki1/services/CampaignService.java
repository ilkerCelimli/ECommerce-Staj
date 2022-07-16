package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.CampaignInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CampaignService extends BaseServices<Campaign>{

    CampaignInfo findCampaignProductId(String id,String shopper);
    boolean addCampaign(AddCampaignDto dto) throws SqlExceptionCustom;
    boolean updateCampaign(AddCampaignDto dto);
    boolean deleteCampaign(String productId,String shopperId) throws SqlExceptionCustom;
    List<CampaignInfo> findCampaigns();

}
