package com.vass.assesment.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Product.
 */
@Data
@AllArgsConstructor
@Document(collection = "PRODUCT")
@Builder(toBuilder = true)
public class Product {

  @Id
  @NotNull(message = "id field is mandatory")
  private Long id;

  @NotBlank(message = "name field is mandatory")
  private String name;

  @NotBlank(message = "technology field is mandatory")
  private String technology;

  @NotNull(message = "customerId field is mandatory")
  private Long customerId;
}
