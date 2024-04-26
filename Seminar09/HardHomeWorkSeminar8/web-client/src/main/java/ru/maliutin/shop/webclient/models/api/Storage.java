package ru.maliutin.shop.webclient.models.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Обертка для обращения к api склада товаров.
 */
@Data
@Component
@ConfigurationProperties("api.storage")
public class Storage {

    private String basicUri;

}
