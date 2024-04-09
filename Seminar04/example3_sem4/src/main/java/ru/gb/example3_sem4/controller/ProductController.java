package ru.gb.example3_sem4.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.example3_sem4.model.Product;
import ru.gb.example3_sem4.repository.ProductRepository;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public String getProduct(Model model)
    {
        model.addAttribute("products",productRepository.getProducts());
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(Product p, Model model)
    {
        productRepository.addProduct(p);
        model.addAttribute("products",productRepository.getProducts());
        return "products";
    }

}
