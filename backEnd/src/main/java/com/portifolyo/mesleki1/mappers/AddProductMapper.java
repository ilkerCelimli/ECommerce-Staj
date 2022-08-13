package com.portifolyo.mesleki1.mappers;

import com.portifolyo.mesleki1.dtos.AddProductDto;
import com.portifolyo.mesleki1.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class AddProductMapper {



    public Product toEntity(AddProductDto dto) {
        return new Product(dto.getProductName(),dto.getDescription(),dto.getPrice(),dto.getImage());
    }

}
