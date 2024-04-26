package ru.maliutin.shop.webclient.seriveces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.maliutin.shop.webclient.acpect.LogLeadTime;
import ru.maliutin.shop.webclient.client.PaymentClientApi;
import ru.maliutin.shop.webclient.client.StorageClientApi;
import ru.maliutin.shop.webclient.models.Order;
import ru.maliutin.shop.webclient.models.Product;
import ru.maliutin.shop.webclient.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис для осуществления покупки.
 */
@Service
public class ShopService {
    /**
     * Объект клиента Feigen для запросов к api оплаты.
     */
    private final PaymentClientApi paymentApi;
    /**
     * Объект клиента Feigen для запросов к api склада.
     */
    private final StorageClientApi storageApi;
    /**
     * Номер счета магазина.
     */
    private final String shopAccount;


    public ShopService(PaymentClientApi paymentApi,
                       StorageClientApi storageApi,
                       @Value("${shop.account.number}") String shopAccount) {
        this.paymentApi = paymentApi;
        this.storageApi = storageApi;
        this.shopAccount = shopAccount;
    }

    /**
     * Получение все товаров со склада.
     * @return список товаров.
     */
    @LogLeadTime
    public List<Product> getAll(){
        return storageApi.getProducts();
    }

    /**
     * Метод покупки товара. На каждом этапе происходит проверка,
     * в случае получения исключения происходит откат выполненных транзакций.
     * @param productId идентификатор продукта.
     * @param amount количество заказанного продукта.
     * @param sum сумма заказа.
     * @param numberCredit номер счета для списания.
     */
    @LogLeadTime
    public void buyProduct(Long productId, int amount,
                           BigDecimal sum, Long numberCredit){
        ResponseEntity<?> response = productReserve(productId, amount);
        if (response.getStatusCode().is2xxSuccessful()){
            response = payOrder(sum, numberCredit);
            if (response.getStatusCode().is2xxSuccessful()){
                response = productBay(productId, amount);
                if (!response.getStatusCode().is2xxSuccessful()){
                    rollbackPayOrder(sum, numberCredit);
                    rollbackProductReserve(productId, amount);
                }
            }else{
                rollbackProductReserve(productId, amount);
            }
        }
    }

    /**
     * Резервирование продукта на складе.
     * @param id идентификатор продукта.
     * @param amount количество.
     */
    @LogLeadTime
    private ResponseEntity<?> productReserve(Long id, int amount)
            throws HttpClientErrorException {
        return storageApi.reserveProduct(id, new Order(amount));
    }

    /**
     * Служебный метод отката резервирования товара
     * @param id идентификатор товара.
     * @param amount количество.
     */
    @LogLeadTime
    private void rollbackProductReserve(Long id, int amount)
            throws HttpClientErrorException {
        storageApi.rollbackReserve(id, new Order(amount));
    }

    /**
     * Оформление покупки, уменьшение остатка на складе.
     * @param id идентификатор продукта.
     * @param amount количество товара.
     */
    @LogLeadTime
    private ResponseEntity<?> productBay(Long id, int amount)
            throws HttpClientErrorException {
        return storageApi.bay(id, new Order(amount));
    }

    /**
     * Оплата товара
     * @param sum сумма для оплаты.
     * @param numberCredit номер счета списания.
     */
    @LogLeadTime
    private ResponseEntity<?> payOrder(BigDecimal sum, Long numberCredit)
            throws HttpClientErrorException {
        return paymentApi.pay(
                new Transaction(numberCredit, Long.parseLong(shopAccount), sum));
    }

    /**
     * Служебный метод отката произведенной оплаты.
     * @param sum сумма операции.
     * @param numberCredit номер счета.
     */
    @LogLeadTime
    private void rollbackPayOrder(BigDecimal sum, Long numberCredit)
            throws HttpClientErrorException {
        paymentApi.rollbackPay(
                new Transaction(numberCredit, Long.parseLong(shopAccount), sum));
    }
}
