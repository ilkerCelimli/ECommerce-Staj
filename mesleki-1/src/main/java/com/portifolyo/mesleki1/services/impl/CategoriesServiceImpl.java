package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.dtos.AddCategoriesDto;
import com.portifolyo.mesleki1.entity.Categories;
import com.portifolyo.mesleki1.exceptions.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.DataIsExistsException;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.mappers.AddCategoriesMapper;
import com.portifolyo.mesleki1.repository.CategoriesRepository;
import com.portifolyo.mesleki1.repository.projections.CategoriesAndProductsInfo;
import com.portifolyo.mesleki1.services.CategoriesService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriesServiceImpl extends BaseServicesImpl<Categories> implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final AddCategoriesMapper addCategoriesMapper;


    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, AddCategoriesMapper addCategoriesMapper) {
        super(categoriesRepository);
        this.categoriesRepository = categoriesRepository;
        this.addCategoriesMapper = addCategoriesMapper;
    }

    @Override
    public boolean checkCategoriesIsExists(String name) {
        return this.categoriesRepository.existsCategoriesByNameEquals(name);
    }

    @Override
    public List<CategoriesAndProductsInfo> findProductsInCategories() {
        return this.categoriesRepository.findByIsActiveAndIsDeleted(true,false);
    }

    @Override
    public CategoriesAndProductsInfo findCategorieAndProducts(String id) {
        Optional<CategoriesAndProductsInfo> o = this.categoriesRepository.findByIdEqualsAndIsActiveTrueAndIsDeletedFalse(id);
        o.orElseThrow(() -> new NotFoundException());
        return o.get();
    }

    @Override
    public boolean AddCategories(AddCategoriesDto dto) throws SqlExceptionCustom {
        if(checkCategoriesIsExists(dto.getName())) {
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


}
