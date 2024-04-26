package ru.maliutin.shop.webclient.acpect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(ru.maliutin.shop.webclient.acpect.LogLeadTime)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Вызван метод: " + joinPoint.getSignature().getName());
        long timeStart = LocalDateTime.now().getNano();

        Object proceed = joinPoint.proceed();

        long timeFinish = LocalDateTime.now().getNano();
        System.out.println("Метод выполнен за: " + ((timeFinish - timeStart)/1000000) + " миллисекунд.");
        return proceed;
    }

}
