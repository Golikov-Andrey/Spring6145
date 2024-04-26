package ru.maliutin.shop.paymentapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maliutin.shop.paymentapp.models.Account;
import ru.maliutin.shop.paymentapp.models.Transaction;
import ru.maliutin.shop.paymentapp.services.PaymentService;

import java.util.List;

/**
 * Контроллер оплаты.
 */
@RestController
@AllArgsConstructor
public class PaymentController {
    /**
     * Сервис оплаты.
     */
    private final PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok().body(paymentService.getAllAccounts());
    }

    /**
     * Проведение оплаты.
     * @param transaction объект с данными для транзакции.
     * @return ответ с подтверждением.
     */
    @PostMapping()
    public ResponseEntity<Void> transaction(@RequestBody Transaction transaction){
        paymentService.transaction(transaction);
        return ResponseEntity.ok().body(null);
    }

    /**
     * Откат произведенной транзакции.
     * @param transaction объект с данными для транзакции.
     * @return ответ с подтверждением.
     */
    @PostMapping("/rollback")
    public ResponseEntity<Void> rollbackTransaction(@RequestBody Transaction transaction){
        paymentService.rollbackTransaction(transaction);
        return ResponseEntity.ok().body(null);
    }
}
