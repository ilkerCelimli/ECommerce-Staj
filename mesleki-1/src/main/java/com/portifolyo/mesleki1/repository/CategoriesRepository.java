package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.projections.ProductsInCategories;

import java.util.List;

public interface CategoriesRepository extends BaseRepository<Categories> {
    List<ProductsInCategories> findByIdEqualsOrderByProducts_NameDesc(String id);
    boolean existsCategoriesByNameEquals(String name);



}
