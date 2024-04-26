package ru.maliutin.shop.webclient.seriveces;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Интерфейс записи данных в файл.
 */
@MessagingGateway(defaultRequestChannel = "inputChanel")
public interface FileGateway {
    /**
     * Метод записи данных.
     * @param filename имя файла.
     * @param data сообщение.
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
}
