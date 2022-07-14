package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.projections.ProductsInCategories;

import java.util.List;

public interface CategoriesService extends BaseServices<Categories>{

    boolean checkCategoriesIsExists(String name);
    List<ProductsInCategories> findProductsInCategories(String id);

}
