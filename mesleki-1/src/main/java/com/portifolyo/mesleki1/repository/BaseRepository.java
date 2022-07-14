package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T,String> {


}
