package ru.maliutin.shop.webclient.seriveces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.maliutin.shop.webclient.acpect.LogLeadTime;
import ru.maliutin.shop.webclient.models.Order;
import ru.maliutin.shop.webclient.models.Product;
import ru.maliutin.shop.webclient.models.Transaction;
import ru.maliutin.shop.webclient.models.api.Payment;
import ru.maliutin.shop.webclient.models.api.Storage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис для осуществления покупки.
 */
@Service
public class ShopService {
    /**+
     * Api оплаты.
     */
    private final Payment paymentApi;
    /**
     * Api склада товаров.
     */
    private final Storage storageApi;
    /**
     * Номер счета магазина.
     */
    private final String shopAccount;

    /**
     * Конструктор класса.
     * @param paymentApi api оплаты.
     * @param storageApi api склада.
     * @param shopAccount номер счета магазина.
     */
    public ShopService(Payment paymentApi, Storage storageApi,
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
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Product>> response = template.exchange(storageApi.getBasicUri(),
        HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        return response.getBody();
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
    public void buyProduct(Long productId, int amount, BigDecimal sum, Long numberCredit){
        productReserve(productId, amount);
        try {
            payOrder(sum, numberCredit);
            try{
                productBay(productId, amount);
            } catch (HttpClientErrorException e){
                rollbackPayOrder(sum, numberCredit);
                rollbackProductReserve(productId, amount);
                throw e;
            }
        }catch (HttpClientErrorException e){
            rollbackProductReserve(productId, amount);
            throw e;
        }
    }

    /**
     * Резервирование продукта на складе.
     * @param id идентификатор продукта.
     * @param amount количество.
     */
    @LogLeadTime
    private void productReserve(Long id, int amount)
            throws HttpClientErrorException {
        RestTemplate template = new RestTemplate();
        String path = storageApi.getBasicUri() + id + "/reserve";
        Order order = new Order();
        order.setAmount(amount);
        template.postForEntity(path, order, Object.class);
    }

    /**
     * Служебный метод отката резервирования товара
     * @param id идентификатор товара.
     * @param amount количество.
     */
    @LogLeadTime
    private void rollbackProductReserve(Long id, int amount)
            throws HttpClientErrorException {
        RestTemplate template = new RestTemplate();
        String path = storageApi.getBasicUri() + id + "/reserve/rollback";
        Order order = new Order();
        order.setAmount(amount);
        template.postForEntity(path, order, Object.class);
    }

    /**
     * Оформление покупки, уменьшение остатка на складе.
     * @param id идентификатор продукта.
     * @param amount количество товара.
     */
    @LogLeadTime
    private void productBay(Long id, int amount)
            throws HttpClientErrorException {
        RestTemplate template = new RestTemplate();
        Order order = new Order();
        order.setAmount(amount);
        template.postForEntity(storageApi.getBasicUri() + id,
                order, Object.class);
    }

    /**
     * Оплата товара
     * @param sum сумма для оплаты.
     * @param numberCredit номер счета списания.
     */
    @LogLeadTime
    private void payOrder(BigDecimal sum, Long numberCredit)
            throws HttpClientErrorException {
        RestTemplate template = new RestTemplate();
        Transaction transaction = new Transaction();
        transaction.setCreditNumber(numberCredit);
        transaction.setDebitNumber(Long.parseLong(shopAccount));
        transaction.setSum(sum);
        template.postForEntity(paymentApi.getBasicUri(),
                transaction, Object.class);
    }

    /**
     * Служебный метод отката произведенной оплаты.
     * @param sum сумма операции.
     * @param numberCredit номер счета.
     */
    @LogLeadTime
    private void rollbackPayOrder(BigDecimal sum, Long numberCredit)
            throws HttpClientErrorException {
        RestTemplate template = new RestTemplate();
        Transaction transaction = new Transaction();
        transaction.setCreditNumber(numberCredit);
        transaction.setDebitNumber(Long.parseLong(shopAccount));
        transaction.setSum(sum);
        template.postForEntity(paymentApi.getBasicUri() + "/rollback",
                transaction, Object.class);
    }
}
