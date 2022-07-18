package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Shopper;

import java.util.List;
import java.util.Optional;

public interface ShopperRepository extends BaseRepository<Shopper> {
 //   Optional<Shopper> findByProductList_NameEquals(String name);
  //  boolean existsByProductList_NameEquals(String name);
   // List<ShopperInfo> findByIsActiveAndIsDeleted(boolean isActive, boolean isDeleted);
   // Optional<ShopperInfo> findShopperByIdEquals(String id);
    Optional<Shopper> findByUser_IdEquals(String id);









}
