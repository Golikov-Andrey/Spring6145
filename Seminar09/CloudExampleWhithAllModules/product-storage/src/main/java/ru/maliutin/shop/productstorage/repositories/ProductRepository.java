package ru.maliutin.shop.productstorage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maliutin.shop.productstorage.models.Product;

/**
 * Репозиторий для работы с сущностью товара.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



}
