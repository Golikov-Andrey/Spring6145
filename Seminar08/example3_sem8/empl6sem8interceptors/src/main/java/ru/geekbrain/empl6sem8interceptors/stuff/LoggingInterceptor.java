package ru.geekbrain.empl6sem8interceptors.stuff;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Start Time: " + System.currentTimeMillis());
        // Продолжить выполнение цепочки перехватчиков и обработку запроса
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("End Time: " + System.currentTimeMillis());
        System.out.println("=======================================");
    }

}
