package com.example.example_2_seminar_5.controller;

import com.example.example_2_seminar_5.model.Book;
import com.example.example_2_seminar_5.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }


}
