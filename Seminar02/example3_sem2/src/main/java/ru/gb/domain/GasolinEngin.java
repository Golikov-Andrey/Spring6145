package ru.gb.domain;

import ru.gb.interfaces.iEngin;
import org.springframework.stereotype.Component;

@Component
public class GasolinEngin implements iEngin {
    public GasolinEngin() {
        System.out.println("Создан экземпляр GasolinEngin");
    }
    @Override
    public void startEngine() {
        System.out.println("Запущен Бензин 44р.л");
    }
}
