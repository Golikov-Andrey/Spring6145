package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.ProjectConfig;
import ru.gb.domain.Car;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

//        Car myCar = new Car();
//        myCar.setModel("X1");
//        myCar.setMade("BMW");
//        System.out.println(myCar);

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

//        Car simpleCar1 = context.getBean(Car.class);
//        System.out.println(simpleCar1);

        Car simpleCar1 = context.getBean("BMW",Car.class);
        System.out.println("simpleCar1 - " + simpleCar1);

        Car simpleCar2 = context.getBean("BMW",Car.class);
        System.out.println("simpleCar2 - " + simpleCar2);

        Car simpleCar3 = context.getBean("Audi",Car.class);
        System.out.println("simpleCar3 - " + simpleCar3);

        Car simpleCar4 = context.getBean("Audi",Car.class);
        System.out.println("simpleCar4 - " + simpleCar4);

//        String s = context.getBean(String.class);
//        System.out.println(s);
//
//        Integer num = context.getBean(Integer.class);
//        System.out.println(num);
    }
}