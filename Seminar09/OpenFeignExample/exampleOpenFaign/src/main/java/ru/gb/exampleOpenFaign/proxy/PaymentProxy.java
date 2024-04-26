package ru.gb.exampleOpenFaign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.gb.exampleOpenFaign.model.Payment;

@FeignClient(name = "payments",url = "http://localhost:8080")
public interface PaymentProxy {
    @PostMapping("/payment")
    Payment createPayment(@RequestHeader String requestId, @RequestBody Payment payment);
}
