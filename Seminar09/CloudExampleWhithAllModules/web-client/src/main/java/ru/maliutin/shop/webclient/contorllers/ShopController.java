package ru.maliutin.shop.webclient.contorllers;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.maliutin.shop.webclient.facade.ShopFacade;
import ru.maliutin.shop.webclient.models.Product;
import ru.maliutin.shop.webclient.seriveces.FileGateway;
import ru.maliutin.shop.webclient.seriveces.ShopService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Контроллер магазина.
 */

@Controller
@AllArgsConstructor
public class ShopController {
    /**
     * Объект срывающий сложную логику. Паттерн Фасад.
     */
    private final ShopFacade shopFacade;

    /**
     * Домашняя страница.
     * @param model модель для передачи данных представлению.
     * @return домашнюю страницу.
     */
    @Timed("getProductTime")
    @GetMapping("/")
    public String homePage(Model model,
                           @RequestParam(value = "confirm", required = false) String confirm){
        model.addAttribute("products", shopFacade.getProducts());
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
    @Timed("ProductByTime")
    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable("id") Long id,
                             @RequestParam("amount") Integer amount,
                             RedirectAttributes redirectAttributes){
        shopFacade.buyProduct(id, amount);
        redirectAttributes.addAttribute("confirm", "Покупка успешно совершена!");
        return "redirect:/";
    }

    /**
     * Страница с ошибками в ходе покупки товара.
     * @param e объект исключения.
     * @param model модель для передачи данных представлению.
     * @return страницу с ошибками.
     */
    @ExceptionHandler(RuntimeException.class)
    public String errorPage(RuntimeException e, Model model){
        model.addAttribute("message", e.getMessage());
        model.addAttribute("products", shopFacade.getProducts());
        return "home";
    }
}
