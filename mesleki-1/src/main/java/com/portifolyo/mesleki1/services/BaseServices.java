package com.portifolyo.mesleki1.services;

import com.portifolyo.mesleki1.entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface BaseServices<T extends BaseEntity>{

    T save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(String id) throws SQLException;
    T findById(String id);
    List<T> findAll();



}
