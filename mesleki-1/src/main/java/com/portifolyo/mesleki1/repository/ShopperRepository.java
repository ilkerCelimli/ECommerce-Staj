package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Shopper;

import java.util.Optional;

public interface ShopperRepository extends BaseRepository<Shopper> {

    Optional<Shopper> findByUser_IdEquals(String id);









}
