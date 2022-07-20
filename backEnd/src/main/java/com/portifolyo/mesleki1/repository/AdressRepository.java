package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.Adress;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdressRepository extends BaseRepository<Adress>{

    @Query("select a from Adress a where a.user.id = ?1")
    List<Adress> findAdressByUserId(String id);

    @Query("select a from Adress a where a.title = ?1 and a.user.id = ?2 ")
    Optional<Adress> findAdressbyTitleAndUserId(String title, String userId);


}
