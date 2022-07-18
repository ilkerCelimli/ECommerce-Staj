package com.portifolyo.mesleki1.repository.projections.projeciton.converters;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesApiInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesApiInfoMapper {

    public CategoriesApiInfo toDto(Categories categories, List<ProductInfo> list){
        return new CategoriesApiInfo(categories.getId(), categories.getName(), categories.getDescription(),list );
    }

}
