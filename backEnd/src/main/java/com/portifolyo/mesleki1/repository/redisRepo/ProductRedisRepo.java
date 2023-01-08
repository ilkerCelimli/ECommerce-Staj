package com.portifolyo.mesleki1.repository.redisRepo;

import com.portifolyo.mesleki1.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepo extends CrudRepository<Product,String> {
}
