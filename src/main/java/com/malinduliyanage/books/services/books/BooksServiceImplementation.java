package com.malinduliyanage.books.services.books;

import com.malinduliyanage.books.constants.BooksResponses;
import com.malinduliyanage.books.constants.CommonResponse;
import com.malinduliyanage.books.dtos.books.BooksDTO;
import com.malinduliyanage.books.dtos.requests.books.AddBookRequest;
import com.malinduliyanage.books.dtos.requests.books.UpdateBookRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;
import com.malinduliyanage.books.entities.BookEntity;
import com.malinduliyanage.books.repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

//A Bean is just an object managed by Spring Container
//@Service is a type of configuring Beans. Also, @Component is possible
//These are automatically managed by Spring, therefore, no need to mention this in main Application.java
@Service
public class BooksServiceImplementation implements BooksService{

    private final BookRepository bookRepository;
    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(BooksServiceImplementation.class);

    @Value("${urls.quotes_api}")
    private String jokeApi;

    public BooksServiceImplementation(BookRepository bookRepository, WebClient webClient) {
        this.bookRepository = bookRepository;
        this.webClient = webClient;
    }

    public BaseResponse<ListBooksResponse> listBooks(){
        try {

            List<BooksDTO> books = bookRepository.findAll()
                    .stream().map(BooksDTO::new).toList();

            if (books.isEmpty()) {
                return new BaseResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, CommonResponse.RECORD_NOT_FOUND.getValue());
            }

            return new BaseResponse<ListBooksResponse>(HttpStatus.OK, new ListBooksResponse(books));
        }
        catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }

    public BaseResponse<String> addBook(AddBookRequest request){
        try {
            BookEntity existingBook = bookRepository.findByBookName(request.getBookName());

            if (existingBook != null) {
                return new BaseResponse<>(HttpStatus.OK, BooksResponses.BOOK_EXIST.getValue());
            }

            BookEntity newBook = new BookEntity();
            newBook.setBookName(request.getBookName());
            newBook.setAuthorName(request.getAuthorName());
            newBook.setDescription(request.getDescription());

            bookRepository.save(newBook);

            return new BaseResponse<>(HttpStatus.OK, BooksResponses.BOOK_CREATED.getValue());

        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }

    public BaseResponse<String> updateBook(int id, UpdateBookRequest request){
        try {
            BookEntity existingBook = bookRepository.findById(id);

            if (existingBook != null) {
                existingBook.setBookName(request.getBookName());
                existingBook.setAuthorName(request.getAuthorName());
                existingBook.setDescription(request.getDescription());

                bookRepository.save(existingBook);
                return new BaseResponse<>(HttpStatus.OK, BooksResponses.BOOK_UPDATED.getValue());
            }

            return new BaseResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, BooksResponses.BOOK_NO_EXIST.getValue());

        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }

    public BaseResponse<String> quoteOfTheDay() {
        try {
            String quote = webClient
                    .get()
                    .uri(jokeApi)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return new BaseResponse<>(HttpStatus.OK, quote);

        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }
}
