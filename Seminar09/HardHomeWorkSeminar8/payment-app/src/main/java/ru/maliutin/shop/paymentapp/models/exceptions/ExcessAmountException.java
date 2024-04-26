package ru.maliutin.shop.paymentapp.models.exceptions;

/**
 * Превышение остатка на счету.
 */
public class ExcessAmountException extends RuntimeException {
    public ExcessAmountException(String message) {
        super(message);
    }
}
