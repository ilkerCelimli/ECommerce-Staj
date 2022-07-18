package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product>{
    Optional<Product> findByIdEqualsAndShopper_IdEquals(String id, String id1);

    Optional<Product> findByNameEqualsAndShopper_IdEquals(String name, String id);

    @Query("select p from Product p where p.shopper.id = ?1 and p.isActive = true and p.isDeleted = false")
    List<Product> findProductInfo(String id);

    @Query("select (count(p) > 0) from Product p where p.name = ?1 and p.shopper.id = ?2")
    boolean checkProductsForShoppers(String name, String id);

    @Query("select p from Product p where p.categories.id = ?1")
    List<Product> findProductsByCategories(String id);

    @Query("select p from Product p where p.id = ?1 and p.shopper.id = ?2")
    Optional<Product> findByProductByProductIdAndShopperId(String product,String shopper);








}
