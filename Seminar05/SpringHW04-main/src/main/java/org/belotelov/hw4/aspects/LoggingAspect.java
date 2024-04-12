package org.belotelov.hw4.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Around("@annotation(TrackUserAction)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();

        logger.info("Method " + methodName + " was called with parameters " +
                Arrays.asList(args));

        return joinPoint.proceed();
    }
}
