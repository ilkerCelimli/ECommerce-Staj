package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddCampaignDto;
import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;

import java.util.List;

public interface CampaignService extends BaseServices<Campaign>{

    void addCampaign(AddCampaignDto dto) throws SqlExceptionCustom;
    void updateCampaign(AddCampaignDto dto);
    void deleteCampaign(String productId) throws SqlExceptionCustom;
     List<CampaignInfo> findCampaigns();
     CampaignInfo findCampaignProductId(String product);
}
