package ru.gb.domain;

import ru.gb.interfaces.iEngin;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private String model = "BMW";
    private String made = "X1";


    private iEngin carEngin;

    public Car(iEngin engin ) {
        this.carEngin = engin;
        System.out.println("Автомобиль создан!");
    }

    public iEngin getCarEngin() {
        return carEngin;
    }

    public void setCarEngin(iEngin carEngin) {
        this.carEngin = carEngin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", made='" + made + '\'' +
                '}';
    }

    public void go()
    {
        carEngin.startEngine();
    }
}
