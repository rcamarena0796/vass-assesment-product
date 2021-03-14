package com.vass.assesment.product.service.impl;

import com.vass.assesment.product.dao.ProductRepository;
import com.vass.assesment.product.model.Product;
import com.vass.assesment.product.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl.
 */
@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product saveOrUpdateProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void deleteProduct(Product product) {
    productRepository.delete(product);
  }

  @Override
  public List<Product> getProductsByCustomerId(Long customerId) {
    return productRepository.findAllByCustomerId(customerId);
  }
}
