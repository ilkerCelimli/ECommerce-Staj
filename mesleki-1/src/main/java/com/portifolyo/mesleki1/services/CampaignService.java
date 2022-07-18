package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;

import java.util.List;

public interface CampaignService extends BaseServices<Campaign>{

    boolean addCampaign(AddCampaignDto dto) throws SqlExceptionCustom;
    boolean updateCampaign(AddCampaignDto dto);
    boolean deleteCampaign(String productId,String shopperId) throws SqlExceptionCustom;
     List<CampaignInfo> findCampaigns();
     CampaignInfo findCampaignProductId(String product);
}
