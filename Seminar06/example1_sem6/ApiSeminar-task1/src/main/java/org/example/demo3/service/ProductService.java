package org.example.demo3.service;

import org.example.demo3.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Product product);

    Product createProduct(Product product);

    void deleteProduct(Long id);
}
