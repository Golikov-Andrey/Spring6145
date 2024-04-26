package ru.maliutin.shop.webclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.maliutin.shop.webclient.models.Order;
import ru.maliutin.shop.webclient.models.Product;

import java.util.List;

@FeignClient(name = "storage")
public interface StorageClientApi {

    @GetMapping
    List<Product> getProducts();

    @PostMapping("/{id}/reserve")
    ResponseEntity<?> reserveProduct(@PathVariable Long id, @RequestBody Order order);

    @PostMapping("/{id}/reserve/rollback")
    void rollbackReserve(@PathVariable Long id, @RequestBody Order order);

    @PostMapping("/{id}")
    ResponseEntity<?> bay(@PathVariable Long id, @RequestBody Order order);

}
