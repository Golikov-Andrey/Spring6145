package ru.gb_spring.homeworkspring_05.controllers;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.gb_spring.homeworkspring_05.model.Task;
import ru.gb_spring.homeworkspring_05.model.TaskStatus;
import ru.gb_spring.homeworkspring_05.services.TaskServices;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Обработчик загрузки задач из CSV файла
 */
@Controller
@Log
public class UploadTask {

    @Autowired
    TaskServices ts;

    /**
     * Обработка POST запроса на добавление задач из CSV файла
     *
     * @param file - csv файл с задачами
     * @return - на стартовую страницу
     */
    @PostMapping("/upload")
    public String uploadCsvData(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Task task = new Task();
            task.setDescription(record.getString("DESCRIPTION"));
            task.setStatus(TaskStatus.valueOf(record.getString("STATUS")));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(record.getString("CREATED_DATE"), formatter);
            task.setCreatedDate(date);
            ts.saveTask(task);
        });
        return "redirect:/";
    }
}
