package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Campaign;

import java.util.Optional;

public interface CampaignRepository extends BaseRepository<Campaign>{
    Optional<Campaign> findByProduct_IdEquals(String id);

    Optional<Campaign> findByProduct_IdEqualsAndProduct_Shopper_IdEquals(String product, String shopper);




}
