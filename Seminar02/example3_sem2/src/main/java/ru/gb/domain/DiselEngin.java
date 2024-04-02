package ru.gb.domain;

import ru.gb.interfaces.iEngin;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Lazy
public class DiselEngin implements iEngin {
    public DiselEngin() {
        System.out.println("Создан экземпляр DiselEngin");
    }

    public void startEngine() {
        System.out.println("Запущен Дизел 65р.л");
    }
}
