package ru.maliutin.shop.webclient.contorllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.maliutin.shop.webclient.models.Product;
import ru.maliutin.shop.webclient.seriveces.ShopService;

import java.math.BigDecimal;

/**
 * Контроллер магазина.
 */

@Controller
@AllArgsConstructor
public class ShopController {
    /**
     * Сервис магазина.
     */
    private final ShopService shopService;

    /**
     * Домашняя страница.
     * @param model модель для передачи данных представлению.
     * @return домашнюю страницу.
     */
    @GetMapping("/")
    public String homePage(Model model,
                           @RequestParam(value = "confirm", required = false) String confirm){
        model.addAttribute("products", shopService.getAll());
        if (confirm != null){
            model.addAttribute("confirm", confirm);
        }
        return "home";
    }

    /**
     * Покупка продукта.
     * @param id идентификатор товара.
     * @param amount количество товара.
     * @return перенаправление на домашнюю страницу.
     */
    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable("id") Long id,
                             @RequestParam("amount") Integer amount,
                             RedirectAttributes redirectAttributes){
        Product product = shopService.getAll()
                .stream()
                .filter(prod -> prod.getId().equals(id))
                .findFirst()
                .orElse(null);
        BigDecimal sum = product.getPrice().multiply(new BigDecimal(amount));
        shopService.buyProduct(product.getId(), amount, sum, 1L); // TODO Временно добавлен номер счета клиента
        redirectAttributes.addAttribute("confirm", "Покупка успешно совершена!");
        return "redirect:/";
    }

    /**
     * Страница с ошибками в ходе покупки товара.
     * @param e объект исключения.
     * @param model модель для передачи данных представлению.
     * @return страницу с ошибками.
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public String errorPage(HttpClientErrorException e, Model model){
        model.addAttribute("message", e.getMessage());
        model.addAttribute("products", shopService.getAll());
        return "home";
    }

}
