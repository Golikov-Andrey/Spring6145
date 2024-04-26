package ru.maliutin.shop.productstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductStorageApplication.class, args);
    }

}
