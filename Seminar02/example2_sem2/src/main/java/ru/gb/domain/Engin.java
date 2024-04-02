package ru.gb.domain;

import org.springframework.stereotype.Component;


@Component
public class Engin {
    private String typeEngin = "Бензиновый";

//    public Engin() {
//
//        System.out.println("Двигатель создан!");
//    }

    public String getTypeEngin() {
        return typeEngin;
    }

    public void setTypeEngin(String typeEngin) {
        this.typeEngin = typeEngin;
    }

    @Override
    public String toString() {
        return "Engin{" +
                "typeEngin='" + typeEngin + '\'' +
                '}';
    }
}
