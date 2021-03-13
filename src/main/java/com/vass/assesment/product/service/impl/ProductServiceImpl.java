package com.vass.assesment.product.service.impl;

import com.vass.assesment.product.dao.ProductRepository;
import com.vass.assesment.product.model.Product;
import com.vass.assesment.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProductsByCustomerId(String customerId) {
        return productRepository.findAllByCustomerId(customerId);
    }
}
