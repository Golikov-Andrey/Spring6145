package ru.maliutin.shop.webclient.config;

import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.maliutin.shop.webclient.client.PaymentClientApi;
import ru.maliutin.shop.webclient.client.StorageClientApi;
import ru.maliutin.shop.webclient.seriveces.ShopService;

/**
 * Класс конфигурации тестирования.
 */
@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    @Bean
    @Primary
    public PaymentClientApi paymentClientApi(){
        return Mockito.mock(PaymentClientApi.class);
    }

    @Bean
    @Primary
    public StorageClientApi storageClientApi(){
        return Mockito.mock(StorageClientApi.class);
    }

    @Bean
    @Primary
    public ShopService shopService(){
        return new ShopService(paymentClientApi(), storageClientApi(), "2");
    }
}
