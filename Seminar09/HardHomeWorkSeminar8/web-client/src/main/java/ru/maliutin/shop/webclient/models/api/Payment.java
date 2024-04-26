package ru.maliutin.shop.webclient.models.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Обертка для обращения к api оплаты.
 */
@Data
@Component
@ConfigurationProperties("api.payment")
public class Payment {

    private String basicUri;

}
