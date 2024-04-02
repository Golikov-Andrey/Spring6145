package ru.gb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.gb.domain.Car;
import ru.gb.domain.Engin;

@Configuration
@ComponentScan(basePackages = "ru.gb.domain")
public class ProjectConfig {

//    @Bean
//    Engin engin1()
//    {
//        Engin eng = new Engin();
//        eng.setTypeEngin("Бензиновый");
//        return eng;
//    }
//
//    @Bean("BMW")
//    Car car1()
//    {
//        Car obCar = new Car(engin1());
//        obCar.setModel("X1");
//        obCar.setMade("BMW");
//        return obCar;
//    }
}
