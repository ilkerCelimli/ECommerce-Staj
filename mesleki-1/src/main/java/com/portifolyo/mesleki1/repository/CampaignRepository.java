package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Campaign;
import com.portifolyo.mesleki1.repository.projections.projeciton.CampaignInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends BaseRepository<Campaign>{

    Optional<Campaign> findByProduct_IdEqualsAndProduct_Shopper_IdEquals(String product, String shopper);

    @Query("select c from Campaign c where c.isActive = true and c.isDeleted = false order by c.startDate DESC")
    List<CampaignInfo> findCampaigns();

    @Query("select c from Campaign c where c.product.id = ?1")
    Optional<CampaignInfo> findCampainForProduct(String id);






}
