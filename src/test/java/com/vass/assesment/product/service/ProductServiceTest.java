package com.vass.assesment.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.vass.assesment.product.dao.ProductRepository;
import com.vass.assesment.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class ProductServiceTest {

  @MockBean
  private ProductRepository productRepository;

  @Autowired
  private ProductService productService;

  @Mock
  private static Product product;

  @Mock
  private static List<Product> productList;

  @Mock
  private static List<Product> emptyProductList;


  @BeforeAll
  public static void setUp() {
    productList = new ArrayList<>();
    emptyProductList = new ArrayList<>();
    productList.add(new Product(1L, "product 1", "some tech", 1L));
    productList.add(new Product(2L, "product 2", "some tech", 1L));

    product = Product.builder().id(1L).name("product 1").technology("some tech").customerId(1L)
        .build();
  }

  @Test
  public void createProductShouldReturnOk() {
    when(productRepository.save(Mockito.any())).thenReturn(product);
    Product response = productService.saveOrUpdateProduct(product);

    assertEquals(response.getId(), product.getId());
    assertEquals(response.getName(), product.getName());
    assertEquals(response.getCustomerId(), product.getCustomerId());
    assertEquals(response.getTechnology(), product.getTechnology());
  }

  @Test
  public void getAllShouldReturnOk() {
    Long customerId = 1L;
    when(productRepository.findAllByCustomerId(customerId)).thenReturn(productList);
    List<Product> products = productService.getProductsByCustomerId(customerId);

    assertEquals(products.size(), productList.size());

  }

  @Test
  public void getAllShouldReturnEmpty() {
    Long customerId = 1L;
    when(productRepository.findAllByCustomerId(customerId)).thenReturn(emptyProductList);
    List<Product> products = productService.getProductsByCustomerId(customerId);

    assertEquals(products.size(), 0);

  }

  @Test
  public void whenDelete_thenObjectShouldBeDeleted() {
    final Product prodDelete = new Product(3L, "delete test", "aaa", 1L);
    Optional<Product> optionalProduct = Optional.of(prodDelete);
    productService.deleteProduct(prodDelete);
    Mockito.verify(productRepository, times(1)).delete(prodDelete);
  }

}