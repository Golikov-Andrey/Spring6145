package org.example.demo3.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.demo3.repoeitory.ProductRepo;
import org.example.demo3.model.Product;
import org.example.demo3.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(null);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productById = getProductById(product.getId());

        /*productById.setCount(product.getCount());*/
        productById.setName(product.getName());
        productById.setQuantity(product.getQuantity());


        return productRepo.save(productById);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product productById = getProductById(id);
        productRepo.delete(productById);
    }
}
