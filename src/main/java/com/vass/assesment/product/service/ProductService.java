package com.vass.assesment.product.service;

import com.vass.assesment.product.model.Product;
import java.util.List;

/**
 * ProductService.
 */
public interface ProductService {

  public Product saveOrUpdateProduct(Product product);

  public void deleteProduct(Product product);

  public List<Product> getProductsByCustomerId(Long customerId);

}