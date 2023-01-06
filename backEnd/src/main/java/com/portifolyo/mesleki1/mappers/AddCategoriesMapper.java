package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.entity.Categories;
import org.springframework.stereotype.Component;

@Component
public class AddCategoriesMapper {

    public Categories toEntity(AddCategoriesDto dto) {
        return new Categories(dto.name(),dto.description(),dto.image());
    }

}
