package com.vass.assesment.product.expose.web;

import com.vass.assesment.product.model.Product;
import com.vass.assesment.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(service.saveOrUpdateProduct(product));
    }

    @GetMapping("findByCustomerId/{customerId}")
    public ResponseEntity<List<Product>> getEmail(@PathVariable("customerId") String customerId) {
        try {
            return ResponseEntity.ok().body(service.getProductsByCustomerId(customerId));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
