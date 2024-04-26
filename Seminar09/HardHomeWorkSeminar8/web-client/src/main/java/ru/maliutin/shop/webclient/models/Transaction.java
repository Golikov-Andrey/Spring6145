package ru.maliutin.shop.webclient.models;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Объект с данными для транзакции.
 */
@Data
public class Transaction {

    private Long creditNumber;
    private Long debitNumber;
    private BigDecimal sum;
}
