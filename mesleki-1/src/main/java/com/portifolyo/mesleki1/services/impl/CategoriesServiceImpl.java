package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.DataIsExistsException;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AddCategoriesMapper;
import com.portifolyo.mesleki1.repository.CategoriesRepository;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesApiInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.CategoriesInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.ProductInfo;
import com.portifolyo.mesleki1.repository.projections.projeciton.converters.CategoriesApiInfoMapper;
import com.portifolyo.mesleki1.services.CategoriesService;
import com.portifolyo.mesleki1.services.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriesServiceImpl extends BaseServicesImpl<Categories> implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final AddCategoriesMapper addCategoriesMapper;
    private final CategoriesApiInfoMapper categoriesApiInfoMapper;
    private final ProductService productService;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, AddCategoriesMapper addCategoriesMapper, CategoriesApiInfoMapper categoriesApiInfoMapper, ProductService productService) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
        this.addCategoriesMapper = addCategoriesMapper;
        this.categoriesApiInfoMapper = categoriesApiInfoMapper;
        this.productService = productService;
    }

    @Override
    public boolean checkCategoriesIsExists(String name) {
        return this.categoriesRepository.existsCategoriesByNameEquals(name);
    }


    @Override
    public boolean AddCategories(AddCategoriesDto dto) throws SqlExceptionCustom {
        if(!checkCategoriesIsExists(dto.getName())) {
            save(addCategoriesMapper.toEntity(dto));
            return true;
        }
        else throw new DataIsExistsException();
    }

    @Override
    public boolean updateCategories(String id ,AddCategoriesDto dto) throws SqlExceptionCustom {
       Categories categories = findById(id);
       if(Objects.nonNull(dto.getName()) || !Strings.isEmpty(dto.getName()) || !Strings.isBlank(dto.getName())) {
           categories.setName(dto.getName());
       }
       if(Objects.nonNull(dto.getDescription()) || !Strings.isBlank(dto.getDescription()) ||!Strings.isEmpty(dto.getDescription())) {
           categories.setDescription(dto.getDescription());
       }
       update(categories);
       return true;
    }

    @Override
    public CategoriesInfo findCategoriesInfo(String id) {
        Optional<CategoriesInfo> categoriesInfo = this.categoriesRepository.findCategories(id);
        if(categoriesInfo.isPresent()) {
            return categoriesInfo.get();
        }
        else throw new NotFoundException();
    }

    @Override
    public List<CategoriesApiInfo> findCategoriesInfoList() {
        List<CategoriesApiInfo> list = new ArrayList<>();
        List<Categories> l = findAll();
        l.forEach(i -> list.add(categoriesApiInfoMapper.toDto(i,productService.findProductsForCategories(i.getId()))));
        return list;
    }

    public CategoriesApiInfo findCategoriesById(String id) {
        Categories c =findById(id);
        List<ProductInfo> productInfo = productService.findProductsForCategories(id);
        return categoriesApiInfoMapper.toDto(c,productInfo);
    }


}
