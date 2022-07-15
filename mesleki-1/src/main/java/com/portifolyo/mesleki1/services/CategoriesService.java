package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.CategoriesAndProductsInfo;

import java.util.List;

public interface CategoriesService extends BaseServices<Categories>{

    boolean checkCategoriesIsExists(String name);
    List<CategoriesAndProductsInfo> findProductsInCategories();
    CategoriesAndProductsInfo findCategorieAndProducts(String id);
    boolean AddCategories(AddCategoriesDto categories) throws SqlExceptionCustom;
    boolean updateCategories(String id,AddCategoriesDto dto) throws SqlExceptionCustom;

}
