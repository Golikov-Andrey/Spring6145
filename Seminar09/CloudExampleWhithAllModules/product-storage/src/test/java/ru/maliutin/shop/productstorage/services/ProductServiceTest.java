package ru.maliutin.shop.productstorage.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import ru.maliutin.shop.productstorage.models.Product;
import ru.maliutin.shop.productstorage.models.exceptions.ExcessAmountException;
import ru.maliutin.shop.productstorage.models.exceptions.ResourceNotFoundException;
import ru.maliutin.shop.productstorage.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    /**
     * Получение всех товаров.
     */
    @Test
    public void getAllProduct(){
        List<Product> products = List.of(new Product());

        given(productRepository.findAll()).willReturn(products);

        List<Product> testListProduct = productService.getAllProduct();

        verify(productRepository).findAll();

        Assertions.assertEquals(products.size(), testListProduct.size());
    }

    /**
     * Получение товара по id.
     */
    @Test
    public void getProductByIdExpectProduct(){
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        Product testProduct = productService.getProductById(productId);
        verify(productRepository).findById(productId);

        Assertions.assertEquals(product, testProduct);
    }

    /**
     * Проверка исключения при отсутствии товара, при запросе по id.
     */
    @Test
    public void getProductByIdExpectException(){
        Long productId = 1L;
        given(productRepository.findById(productId))
                .willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
            productService.getProductById(productId));

        verify(productRepository).findById(productId);
    }

    /**
     * Успешное списание товара.
     */
    @Test
    public void reduceAmountExpectCorrect(){
        Long productId = 1L;
        int amountInOrder = 2;
        int amountInStorage = 5;
        int amountInReserve = 2;
        Product product = new Product();
        product.setId(productId);
        product.setAmount(amountInStorage);
        product.setReserved(amountInReserve);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        productService.reduceAmount(productId, amountInOrder);

        verify(productRepository).findById(productId);
        verify(productRepository).save(product);

        Assertions.assertEquals(amountInStorage - amountInOrder,
                product.getAmount());
        Assertions.assertEquals(amountInReserve - amountInOrder,
                product.getReserved());
    }

    /**
     * Исключение при попытке списать товар, недостаточно на складе.
     */
    @Test
    public void reduceAmountExpectException(){
        Long productId= 1L;
        int amountInOrder = 5;
        int amountInStorage = 1;
        Product product = new Product();
        product.setId(productId);
        product.setAmount(amountInStorage);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        assertThrows(ExcessAmountException.class,
                () -> productService.reduceAmount(productId, amountInOrder));

        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(product);
    }

    /**
     * Успешное резервирование товара.
     */
    @Test
    public void reservedProductExpectCorrect(){
        Long productId = 1L;
        int amountInOrder = 2;
        int amountInStorage = 5;
        Product product = new Product();
        product.setId(productId);
        product.setAmount(amountInStorage);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        productService.reservedProduct(productId, amountInOrder);

        verify(productRepository).findById(productId);
        verify(productRepository).save(product);

        Assertions.assertEquals(amountInOrder, product.getReserved());
    }

    /**
     * Исключение при попытке зарезервировать больше, чем есть на складе.
     */
    @Test
    public void reservedProductExpectException(){
        Long productId= 1L;
        int amountInOrder = 5;
        int amountInStorage = 1;
        Product product = new Product();
        product.setId(productId);
        product.setAmount(amountInStorage);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        assertThrows(ExcessAmountException.class,
                () -> productService.reservedProduct(productId, amountInOrder));

        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(product);
    }

    /**
     * Откат резервирования товара.
     */
    @Test
    public void rollbackReservedProduct(){
        Long productId = 1L;
        int amountInOrder = 1;
        int amountInReserved = 1;
        Product product = new Product();
        product.setId(productId);
        product.setReserved(amountInReserved);

        given(productRepository.findById(productId))
                .willReturn(Optional.of(product));

        productService.rollbackReservedProduct(productId, amountInOrder);

        verify(productRepository).findById(productId);
        verify(productRepository).save(product);

        Assertions.assertEquals(amountInReserved - amountInOrder, product.getReserved());
    }
}
