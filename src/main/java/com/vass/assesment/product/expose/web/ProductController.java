package com.vass.assesment.product.expose.web;

import com.vass.assesment.product.model.Product;
import com.vass.assesment.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController.
 */
@Api(tags = "Product API", value = "CRUD operations for products")
@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  private ProductService service;

  @ApiOperation(value = "Endpoint used to create or update a product")
  @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    return ResponseEntity.ok().body(service.saveOrUpdateProduct(product));
  }

  @ApiOperation(value = "Endpoint used to return all products by customer id")
  @GetMapping("findByCustomerId/{customerId}")
  public ResponseEntity<List<Product>> getEmail(@PathVariable("customerId") Long customerId) {
    return ResponseEntity.ok().body(service.getProductsByCustomerId(customerId));
  }

  @ApiOperation(value = "Endpoint used to delete a product")
  @DeleteMapping("")
  public void deleteProduct(@Valid @RequestBody Product product) {
    service.deleteProduct(product);
  }

  /**
   * handleValidationExceptions.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}