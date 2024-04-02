package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.ProjectConfig;
import ru.gb.domain.Car;
import ru.gb.domain.Engin;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Car simpleCar = context.getBean(Car.class);
        System.out.println(simpleCar);

        Engin eng = context.getBean(Engin.class);
        System.out.println(eng);
    }
}