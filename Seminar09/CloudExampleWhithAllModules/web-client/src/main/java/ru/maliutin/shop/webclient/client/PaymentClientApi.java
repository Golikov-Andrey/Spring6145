package ru.maliutin.shop.webclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.maliutin.shop.webclient.models.Transaction;

/**
 * Клиент для обращения к api payment
 */

@FeignClient(name = "payment")
public interface PaymentClientApi {

    @PostMapping()
    ResponseEntity<?> pay(@RequestBody Transaction transaction);

    @PostMapping("/rollback")
    void rollbackPay(@RequestBody Transaction transaction);

}
