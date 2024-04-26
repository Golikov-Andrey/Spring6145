package ru.maliutin.shop.webclient.models;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Объект с товаром.
 */
public record Product (Long id, String name, int amount, BigDecimal price) {
}
