package ru.maliutin.shop.webclient.models;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Объект с товаром.
 */
@Data
public class Product {

    private Long id;
    private String name;
    private int amount;
    private BigDecimal price;

}
