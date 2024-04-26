package ru.maliutin.shop.paymentapp.models;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Объект для проведения транзакции.
 */
@Data
public class Transaction {

    private Long creditNumber;
    private Long debitNumber;
    private BigDecimal sum;

}
