package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.CategoriesRepository;
import com.portifolyo.mesleki1.repository.projections.ProductsInCategories;
import com.portifolyo.mesleki1.services.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl extends BaseServicesImpl<Categories> implements CategoriesService {

    private final CategoriesRepository categoriesRepository;


    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public boolean checkCategoriesIsExists(String name) {
        return this.categoriesRepository.existsCategoriesByNameEquals(name);
    }

    @Override
    public List<ProductsInCategories> findProductsInCategories(String id) {
        List<ProductsInCategories> list = this.categoriesRepository.findByIdEqualsOrderByProducts_NameDesc(id);
        return list;
    }
}
