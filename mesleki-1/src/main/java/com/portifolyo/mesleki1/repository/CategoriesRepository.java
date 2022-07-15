package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.projections.CategoriesAndProductsInfo;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends BaseRepository<Categories> {
    boolean existsCategoriesByNameEquals(String name);

    List<CategoriesAndProductsInfo> findByIsActiveAndIsDeleted(boolean isActive, boolean isDeleted);

    Optional<CategoriesAndProductsInfo> findByIsActiveIsTrueAndIsDeletedIsFalse();

    Optional<CategoriesAndProductsInfo> findByIdEqualsAndIsActiveTrueAndIsDeletedFalse(String id);



}
