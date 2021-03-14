package com.vass.assesment.product.dao;

import com.vass.assesment.product.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  List<Product> findAllByCustomerId(Long customerId);
}