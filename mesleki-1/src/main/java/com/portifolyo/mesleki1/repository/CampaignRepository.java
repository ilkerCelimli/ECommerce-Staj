package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.repository.projections.CampaignInfo;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends BaseRepository<Campaign>{
    Optional<Campaign> findByProduct_IdEquals(String id);

    Optional<Campaign> findByProduct_IdEqualsAndProduct_Shopper_IdEquals(String product, String shopper);

    Optional<CampaignInfo> findByProduct_IdEqualsAndProduct_Shopper_IdEqualsAndIsActiveTrue(String productId, String shopperId);

    List<CampaignInfo> findByIsActiveTrueOrderByEndDateAsc();




}
