package com.vass.assesment.product.expose.web;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vass.assesment.product.model.Product;
import com.vass.assesment.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  protected ProductService productService;

  private static Product product;


  private static List<Product> productList;


  private static List<Product> emptyProductList;


  @BeforeAll
  public static void setUp() {
    productList = new ArrayList<>();
    emptyProductList = new ArrayList<>();
    productList.add(new Product(1L, "product 1", "some tech", 1L));
    productList.add(new Product(1L, "product 2", "some tech", 1L));

    product = Product.builder().id(1L).name("product 1").technology("some tech").customerId(1L)
        .build();

  }

  @Test
  public void getByCustomerIdShouldReturnOk() throws Exception {
    Long customerId = 1L;
    doReturn(productList).when(productService).getProductsByCustomerId(customerId);
    this.mockMvc.perform(get("/api/product/findByCustomerId/" + customerId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(mapToJson(productList))));
  }

  @Test
  public void getByCustomerIdShouldReturnEmpty() throws Exception {
    Long customerId = 1L;
    doReturn(emptyProductList).when(productService).getProductsByCustomerId(customerId);
    this.mockMvc.perform(get("/api/product/findByCustomerId/" + customerId)).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(mapToJson(emptyProductList))));
  }


  @Test

  public void createProductShouldReturnOk() throws Exception {
    when(productService.saveOrUpdateProduct(Mockito.any())).thenReturn(product);
    this.mockMvc.perform(post("/api/product").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(mapToJson(product))).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(mapToJson(product))));
  }


  @Test
  public void createProductShouldReturnError() throws Exception {
    Product badProd = Product.builder().id(1L).name("product 1").technology("some tech").build();
    when(productService.saveOrUpdateProduct(Mockito.any())).thenReturn(badProd);
    this.mockMvc.perform(post("/api/product").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(mapToJson(badProd))).andDo(print()).andExpect(status().isBadRequest());
  }

  @Test
  public void testDelete() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/product").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(mapToJson(product))).andDo(print()).andExpect(status().isOk())
        .andExpect(status().isOk());
  }


  private String mapToJson(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }

}