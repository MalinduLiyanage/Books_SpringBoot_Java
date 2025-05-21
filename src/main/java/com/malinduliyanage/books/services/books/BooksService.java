package com.malinduliyanage.books.services.books;

import com.malinduliyanage.books.dtos.requests.books.AddBookRequest;
import com.malinduliyanage.books.dtos.requests.books.UpdateBookRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;

public interface BooksService {

    BaseResponse<ListBooksResponse> listBooks();
    BaseResponse<String> addBook(AddBookRequest request);
    BaseResponse<String> updateBook(int id, UpdateBookRequest request);
    BaseResponse<String> quoteOfTheDay();
}
