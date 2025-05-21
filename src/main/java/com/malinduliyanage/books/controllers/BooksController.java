package com.malinduliyanage.books.controllers;

import com.malinduliyanage.books.dtos.requests.books.AddBookRequest;
import com.malinduliyanage.books.dtos.requests.books.UpdateBookRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;
import com.malinduliyanage.books.services.books.BooksService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/list")
    public BaseResponse<ListBooksResponse> getBooks(){
        return booksService.listBooks();
    }

    @PostMapping("/add")
    public BaseResponse<String> addBook(@RequestBody AddBookRequest request){
        return booksService.addBook(request);
    }

    @PostMapping("/update/{id}")
    public BaseResponse<String> updateBook(@PathVariable("id") int id,@RequestBody UpdateBookRequest request){
        return booksService.updateBook(id, request);
    }

    @PostMapping("/quote")
    public BaseResponse<String> quoteOfTheDay(){
        return booksService.quoteOfTheDay();
    }
}
