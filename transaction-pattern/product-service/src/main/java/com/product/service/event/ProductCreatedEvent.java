package com.product.service.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreatedEvent {
    private String productId;  //unique for each product
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
