package com.portifolyo.mesleki1.services.impl;

import com.portifolyo.mesleki1.entity.BaseEntity;
import com.portifolyo.mesleki1.exceptions.apiexception.SqlExceptionCustom;
import com.portifolyo.mesleki1.exceptions.apiexception.NotFoundException;
import com.portifolyo.mesleki1.repository.BaseRepository;
import com.portifolyo.mesleki1.services.BaseServices;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class BaseServicesImpl<T extends BaseEntity> implements BaseServices<T> {


    private final BaseRepository<T> baseRepository;

    protected BaseServicesImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T entity) throws SqlExceptionCustom {
        T e = baseRepository.save(entity);
        if(Strings.isBlank(e.getId()) || Strings.isEmpty(e.getId())){
            SqlExceptionCustom f = new SqlExceptionCustom();
            log.error("Error while saving entity, {} ,{}",f.getErrorCode(),f.getSQLState());
            throw f;
        }
        else {
            log.info("Saved Entity {} {}" ,e.getId(),e.getCreatedAt() );
            return e;
        }

    }

    @Override
    public void update(T entity) throws SqlExceptionCustom {
        Date after = entity.getUpdatedAt();
        entity.setUpdatedAt(new Date());
        T e = baseRepository.save(entity);
        if(e.getUpdatedAt().before(after) || e.getUpdatedAt().equals(after)){
            SqlExceptionCustom f = new SqlExceptionCustom();
            log.error("Error while saving entity, {} ,{}",f.getErrorCode(),f.getSQLState());
            throw f;
        }
        else {
            log.info("Saved Entity {} {}" ,e.getId(),e.getCreatedAt() );
        }
    }

    @Override
    public void delete(String id) throws SqlExceptionCustom {
        T e = findById(id);
        if(e == null) {
            throw new RuntimeException();
        }
        else {
            e.setActive(false);
            update(e);
        }
    }

    @Override
    public T findById(String id) {
        Optional<T> o = baseRepository.findById(id);
        if(o.isPresent()) {
            return o.get();
        }
        else throw new NotFoundException();
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll(Sort.by("createdAt").descending());
    }
}
