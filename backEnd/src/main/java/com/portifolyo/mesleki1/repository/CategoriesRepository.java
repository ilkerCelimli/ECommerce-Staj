package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesInfo;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface CategoriesRepository extends BaseRepository<Categories> {
    boolean existsCategoriesByNameEquals(String name);

    @Query("select c from Categories c where c.isActive = true and c.isDeleted = false and c.id = ?1")
    Optional<CategoriesInfo> findCategories(String id);




}
