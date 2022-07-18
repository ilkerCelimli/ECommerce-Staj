package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Adress;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdressRepository extends BaseRepository<Adress>{

    @Query("select a from Adress a where a.user.id = ?1")
    List<Adress> findAdressByUserId(String id);

}
