package com.portifolyo.mesleki1.repository;

import com.portifolyo.mesleki1.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {


    boolean existsUserByEmail(String email);
    Optional<User> findUserByEmailEquals(String email);
    Optional<User> findUserByActivitionCodeEquals(String code);

}
