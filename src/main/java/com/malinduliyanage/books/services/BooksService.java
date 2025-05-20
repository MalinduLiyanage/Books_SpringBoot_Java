package com.malinduliyanage.books.services;

import com.malinduliyanage.books.constants.BooksResponses;
import com.malinduliyanage.books.constants.CommonResponse;
import com.malinduliyanage.books.dtos.books.BooksDTO;
import com.malinduliyanage.books.dtos.requests.AddBookRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;
import com.malinduliyanage.books.entities.BookEntity;
import com.malinduliyanage.books.repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class BooksService {

    private final BookRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(BooksService.class);

    public BooksService (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BaseResponse<ListBooksResponse> listBooks(){
        try {

            List<BooksDTO> books = bookRepository.findAll()
                    .stream().map(BooksDTO::new).toList();

            if (books.isEmpty()) {
                return new BaseResponse<>(HttpStatus.NO_CONTENT, CommonResponse.RECORD_NOT_FOUND.getValue());
            }

            return new BaseResponse<ListBooksResponse>(HttpStatus.OK, new ListBooksResponse(books));
        }
        catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }

    public BaseResponse<BooksResponses> addBook(AddBookRequest request){
        try {
            BookEntity existingBook = bookRepository.findByBookName(request.getBookName());

            if (existingBook != null) {
                return new BaseResponse<>(HttpStatus.CREATED, BooksResponses.BOOK_EXIST.getValue());
            }

            BookEntity newBook = new BookEntity();
            newBook.setBookName(request.getBookName());
            newBook.setAuthorName(request.getAuthorName());
            newBook.setDescription(request.getDescription());

            bookRepository.save(newBook);

            return new BaseResponse<>(HttpStatus.CREATED, BooksResponses.BOOK_CREATED.getValue());

        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }
}
