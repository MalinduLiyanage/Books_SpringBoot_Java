package com.malinduliyanage.books.controllers;

import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;
import com.malinduliyanage.books.services.BooksService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//We use @RestController to create RESTful web services.
//Automatically adds @ResponseBody to all methods, meaning the returned data (like JSON) is sent directly in the HTTP response body, not as a view name.
@RestController
//RequestMapping maps a specific URL path to a class or method, helps to group related endpoints under one base path.
@RequestMapping("/api/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    //We can map to a specific path, or leave it empty
    @PostMapping("/test")
    public String test(@RequestBody String name) {

        //@RequestBody binds the HTTP request body to a method parameter
        //Without it, Spring will look for a query param or form param like api/books?name=J.K. Rowling

        return "Book " + name + " added!";
    }

    @PostMapping("/list")
    public BaseResponse<ListBooksResponse> getBooks(){
        return booksService.listBooks();
    }
}
