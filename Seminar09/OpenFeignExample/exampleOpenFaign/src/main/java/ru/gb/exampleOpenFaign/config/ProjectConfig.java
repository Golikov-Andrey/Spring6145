package ru.gb.exampleOpenFaign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "ru.gb.exampleOpenFaign.proxy" )
public class ProjectConfig {
}
