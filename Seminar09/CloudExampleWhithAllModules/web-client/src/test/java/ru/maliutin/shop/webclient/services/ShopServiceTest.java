package ru.maliutin.shop.webclient.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.maliutin.shop.webclient.client.PaymentClientApi;
import ru.maliutin.shop.webclient.client.StorageClientApi;
import ru.maliutin.shop.webclient.config.TestConfig;
import ru.maliutin.shop.webclient.models.Order;
import ru.maliutin.shop.webclient.models.Product;
import ru.maliutin.shop.webclient.models.Transaction;
import ru.maliutin.shop.webclient.seriveces.ShopService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ActiveProfiles("test")
@Import(TestConfig.class)
public class ShopServiceTest {

    @MockBean
    private PaymentClientApi paymentClientApi;
    @MockBean
    private StorageClientApi storageClientApi;

    @Autowired
    private ShopService shopService;

    /**
     * Тестирование получения списка товаров от удаленного api.
     */
    @Test
    public void getAll(){
        List<Product> products = List.of(new Product(1L, "some name", 1, new BigDecimal(1)));
        Mockito.when(storageClientApi.getProducts()).thenReturn(products);

        List<Product> testProducts = shopService.getAll();
        verify(storageClientApi).getProducts();

        Assertions.assertEquals(products, testProducts);
    }

    /**
     * Тестирование корректной покупки товара.
     */
    @Test
    public void buyProductExpectCorrect(){
        Long productId = 1L;
        int amount = 1;
        BigDecimal sum = new BigDecimal(100);
        Long numberCredit = 1L;

        Mockito.when(storageClientApi.reserveProduct(anyLong(), any(Order.class)))
                .thenReturn(ResponseEntity.ok().build());
        Mockito.when(paymentClientApi.pay(any(Transaction.class)))
                .thenReturn(ResponseEntity.ok().build());
        Mockito.when(storageClientApi.bay(anyLong(), any(Order.class)))
                .thenReturn(ResponseEntity.ok().build());

        shopService.buyProduct(productId, amount, sum, numberCredit);

        verify(storageClientApi).reserveProduct(anyLong(), any(Order.class));
        verify(paymentClientApi).pay(any(Transaction.class));
        verify(storageClientApi).bay(anyLong(), any(Order.class));

        verify(storageClientApi, never()).rollbackReserve(anyLong(), any(Order.class));
        verify(paymentClientApi, never()).rollbackPay(any(Transaction.class));
    }

    /**
     * Тестирование ошибки продажи товара.
     */
    @Test
    public void buyProductExpectBadBayProduct(){
        Long productId = 1L;
        int amount = 1;
        BigDecimal sum = new BigDecimal(100);
        Long numberCredit = 1L;

        Mockito.when(storageClientApi.reserveProduct(anyLong(), any(Order.class)))
                .thenReturn(ResponseEntity.ok().build());
        Mockito.when(paymentClientApi.pay(any(Transaction.class)))
                .thenReturn(ResponseEntity.ok().build());
        Mockito.when(storageClientApi.bay(anyLong(), any(Order.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        shopService.buyProduct(productId, amount, sum, numberCredit);

        verify(storageClientApi).reserveProduct(anyLong(), any(Order.class));
        verify(paymentClientApi).pay(any(Transaction.class));
        verify(storageClientApi).bay(anyLong(), any(Order.class));
        verify(storageClientApi).rollbackReserve(anyLong(), any(Order.class));
        verify(paymentClientApi).rollbackPay(any(Transaction.class));
    }

    /**
     * Тестирование ошибки оплаты товара.
     */
    @Test
    public void buyProductExpectBadPayOrder(){
        Long productId = 1L;
        int amount = 1;
        BigDecimal sum = new BigDecimal(100);
        Long numberCredit = 1L;

        Mockito.when(storageClientApi.reserveProduct(anyLong(), any(Order.class)))
                .thenReturn(ResponseEntity.ok().build());
        Mockito.when(paymentClientApi.pay(any(Transaction.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        shopService.buyProduct(productId, amount, sum, numberCredit);

        verify(storageClientApi).reserveProduct(anyLong(), any(Order.class));
        verify(paymentClientApi).pay(any(Transaction.class));
        verify(storageClientApi).rollbackReserve(anyLong(), any(Order.class));

        verify(paymentClientApi, never()).rollbackPay(any(Transaction.class));
        verify(storageClientApi, never()).bay(anyLong(), any(Order.class));
    }

    /**
     * Тестирование ошибки резервирования товара.
     */
    @Test
    public void buyProductExpectBadReserve(){
        Long productId = 1L;
        int amount = 1;
        BigDecimal sum = new BigDecimal(100);
        Long numberCredit = 1L;

        Mockito.when(storageClientApi.reserveProduct(anyLong(), any(Order.class)))
                .thenReturn(ResponseEntity.badRequest().build());

        shopService.buyProduct(productId, amount, sum, numberCredit);

        verify(storageClientApi).reserveProduct(anyLong(), any(Order.class));

        verify(paymentClientApi, never()).pay(any(Transaction.class));
        verify(storageClientApi, never()).bay(anyLong(), any(Order.class));
        verify(storageClientApi, never()).rollbackReserve(anyLong(), any(Order.class));
        verify(paymentClientApi, never()).rollbackPay(any(Transaction.class));
    }
}
