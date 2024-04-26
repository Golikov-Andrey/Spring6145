package ru.maliutin.shop.webclient.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maliutin.shop.webclient.models.Product;
import ru.maliutin.shop.webclient.seriveces.FileGateway;
import ru.maliutin.shop.webclient.seriveces.ShopService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopFacade {

    /**
     * Сервис магазина.
     */
    private final ShopService shopService;
    /**
     * Сервис объекта интеграции.
     */
    private final FileGateway fileGateway;
    /**
     * Имя файла для записи данных.
     */
    private final String FILE_NAME = "requests.txt";

    /**
     * Получение списка товаров.
     * @return список товаров.
     */
    public List<Product> getProducts(){
        fileGateway.writeToFile(FILE_NAME, getDateTime() + " вызван список товаров.");
        return shopService.getAll();
    }

    /**
     * Покупка товара.
     * @param id идентификатор товара.
     * @param amount количество.
     */
    public void buyProduct(Long id, Integer amount){
        Product product = shopService.getAll()
                .stream()
                .filter(prod -> prod.id().equals(id))
                .findFirst()
                .orElse(null);
        BigDecimal sum = product.price().multiply(new BigDecimal(amount));
        StringBuilder message = new StringBuilder();
        message.append(getDateTime())
                .append(" пользователь производит попытку покупки товара: ")
                .append(product)
                .append(" в количестве ")
                .append(amount)
                .append(" шт.");
        fileGateway.writeToFile(FILE_NAME, message.toString());
        shopService.buyProduct(product.id(), amount, sum, 1L); // TODO Временно добавлен номер счета клиента
    }

    private String getDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm");
        return dateTime.format(formatter);
    }
}
