package com.portifolyo.mesleki1.repository.projections.projeciton.converters;

import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesInfo;
import org.springframework.stereotype.Component;

@Component
public class CategoriesInfoMapper {

    public CategoriesInfo toDto(Categories categories) {
        return new CategoriesInfo(categories.getId(),categories.getName(), categories.getDescription(),categories.getImage());
    }

}
