package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Product;

import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product>{
    Optional<Product> findByIdEqualsAndShopper_IdEquals(String id, String id1);

    Optional<Product> findByNameEqualsAndShopper_IdEquals(String name, String id);




}
