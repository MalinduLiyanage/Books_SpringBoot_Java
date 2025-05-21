package com.malinduliyanage.books.controllers;

import com.malinduliyanage.books.dtos.requests.AddBookRequest;
import com.malinduliyanage.books.dtos.requests.UpdateBookRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;
import com.malinduliyanage.books.services.books.BooksServiceImplementation;
import org.springframework.web.bind.annotation.*;

//We use @RestController to create RESTful web services.
//Automatically adds @ResponseBody to all methods, meaning the returned data (like JSON) is sent directly in the HTTP response body, not as a view name.
@RestController
//RequestMapping maps a specific URL path to a class or method, helps to group related endpoints under one base path.
@RequestMapping("/api/books")
public class BooksController {

    private final BooksServiceImplementation booksServiceImplementation;

    public BooksController(BooksServiceImplementation booksServiceImplementation) {
        this.booksServiceImplementation = booksServiceImplementation;
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
        return booksServiceImplementation.listBooks();
    }

    @PostMapping("/add")
    public BaseResponse<String> addBook(@RequestBody AddBookRequest request){
        return booksServiceImplementation.addBook(request);
    }

    @PostMapping("/update/{id}")
    public BaseResponse<String> updateBook(@PathVariable("id") int id,@RequestBody UpdateBookRequest request){
        return booksServiceImplementation.updateBook(id, request);
    }
}
