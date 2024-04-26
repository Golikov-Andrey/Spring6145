package ru.maliutin.shop.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Конфигурация интеграции.
 */
@Configuration
public class IntegrationConfig {
    /**
     * Входной канал.
     * @return
     */
    @Bean
    public MessageChannel inputChanel(){
        return new DirectChannel();
    }

    /**
     * Канал записи в файл.
     * @return
     */
    @Bean
    public MessageChannel fileWriterChanel(){
        return new DirectChannel();
    }

    /**
     * Трансформер преобразования данных.
     * @return
     */
    @Bean
    @Transformer(inputChannel = "inputChanel", outputChannel = "fileWriterChanel")
    public GenericTransformer<String, String> transformer(){
        return text -> text;
    }

    /**
     * Запись данных в файл.
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler messageHandler(){
        String rootPath = System.getProperty("user.dir");
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File(rootPath + "/web-client/files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
