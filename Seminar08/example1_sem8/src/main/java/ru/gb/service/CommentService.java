package ru.gb.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.aspect.ToLog;
import ru.gb.model.Comment;

@Component
public class CommentService {

    @ToLog
    public String publishComment(Comment comment) {
        System.out.println("Опубликовать комментарий: " + comment.getText()
                + " автор: "+comment.getAuthor());
        return "SUCCESS";
    }
}
