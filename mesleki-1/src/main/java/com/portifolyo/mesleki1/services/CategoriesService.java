package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesApiInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesInfo;

import java.util.List;

public interface CategoriesService extends BaseServices<Categories>{

    boolean checkCategoriesIsExists(String name);
    void AddCategories(AddCategoriesDto categories) throws SqlExceptionCustom;
    void updateCategories(String id,AddCategoriesDto dto) throws SqlExceptionCustom;
    CategoriesInfo findCategoriesInfo(String id);
    List<CategoriesApiInfo> findCategoriesInfoList();

}
