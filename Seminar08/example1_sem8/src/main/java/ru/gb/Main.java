package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.ProjectConfiguration;
import ru.gb.model.Comment;
import ru.gb.service.CommentService;

public class Main {
    public static void main(String[] args) {
        var c = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        var service = c.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Первый комментарий");
        comment.setAuthor("Юля");

        String value = service.publishComment(comment);
        System.out.println(service.getClass());
        System.out.println(value);

    }
}