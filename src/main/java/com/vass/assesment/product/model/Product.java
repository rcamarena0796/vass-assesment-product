package com.vass.assesment.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "PRODUCT")
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Product {
    @Id
    private String id;
    private String name;
    private String tecnology;
    private String customerId;
}
