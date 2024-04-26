package ru.maliutin.shop.productstorage.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.maliutin.shop.productstorage.models.exceptions.ExcessAmountException;
import ru.maliutin.shop.productstorage.models.exceptions.ResourceNotFoundException;
import ru.maliutin.shop.productstorage.models.Product;
import ru.maliutin.shop.productstorage.repositories.ProductRepository;

import java.util.List;

/**
 * Сервис для работы с товарами.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    /**
     * Объект репозитория.
     */
    private final ProductRepository productRepository;

    /**
     * Получение всех товаров.
     * @return список товаров.
     */
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    /**
     * Получение данных о конкретном товаре на складе.
     * @param id идентификатор товара.
     * @return объект товара.
     * @throws ResourceNotFoundException исключение при отсутствии товара.
     */
    public Product getProductById(Long id) throws  ResourceNotFoundException{
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Товар " + id + " не найден!"));
    }

    /**
     * Уменьшение остатка товара на складе.
     * @param id идентификатор товара.
     * @param amount количество для уменьшения.
     * @throws ExcessAmountException исключение при превышении остатка.
     */

    @Transactional
    public void reduceAmount(@PathVariable Long id, int amount)
            throws ExcessAmountException{
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setAmount(product.getAmount() - amount);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }

    /**
     * Резервирование товара на складе.
     * @param id идентификатор товара.
     * @param amount количество заказа.
     * @throws ExcessAmountException исключение при превышении остатка.
     */
    @Transactional
    public void reservedProduct(Long id, int amount) throws ExcessAmountException{
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setReserved(amount);
        productRepository.save(product);
    }

    /**
     * Откат резервирования товара на складе.
     * @param id идентификатор товара.
     * @param amount количество.
     */
    @Transactional
    public void rollbackReservedProduct(Long id, int amount){
        Product product = getProductById(id);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }
}
