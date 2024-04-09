package ru.gb.example3_sem4.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.gb.example3_sem4.model.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product)
    {
        products.add(product);
    }

    public List<Product> getProducts()
    {
        return products;
    }

}
