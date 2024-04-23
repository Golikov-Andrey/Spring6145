package ru.gb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import ru.gb.model.Comment;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    //https://docs.spring.io/spring-framework/reference/core/aop/ataspectj.html
//    @Around("execution(* ru.gb.service.*.*(..))")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        System.out.println("Сработал аспект до метода ");
//
//        Object returnedByMethod = joinPoint.proceed();
//
//        System.out.println("Сработал аспект после метода ");
//
//        return  "Другая запись!";//returnedByMethod;
//
//    }

    @AfterReturning(value = "@annotation(ToLog)", returning = "returnedValue")
    public void log(Object returnedValue) {
        System.out.println("Method executed and returned " + returnedValue);
    }


    @Around("execution(* ru.gb.service.*.*(..))")
    @Order(1)
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();

        System.out.println("Method " + methodName +
                " with parameters " + Arrays.asList(arguments) +
                " will execute");

        System.out.println("Сообщение до вызова декаоируемого метода");


        Comment comment = new Comment();
        comment.setAuthor("Jenny");
        comment.setText("Some other text!");
        Object [] newArguments = {comment};


        Object returnedByMethod = joinPoint.proceed(newArguments);
        System.out.println("Сообщение после вызова декаоируемого метода");


        return returnedByMethod;
    }

    @Around("execution(* ru.gb.service.*.*(..))")
    @Order(2)
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() -start;
        System.out.println("Метод: "+joinPoint.getSignature().getName() + " - "+elapsedTime+" милсек");
        return result;
    }


}
