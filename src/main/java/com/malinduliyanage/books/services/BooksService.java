package com.malinduliyanage.books.services;

import com.malinduliyanage.books.constants.CommonResponse;
import com.malinduliyanage.books.dtos.books.BooksDTO;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.books.ListBooksResponse;
import com.malinduliyanage.books.repositories.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                return new BaseResponse<>(HttpStatus.NO_CONTENT, CommonResponse.RECORD_NOT_FOUND);
            }

            return new BaseResponse<>(HttpStatus.OK, new ListBooksResponse(books));
        }
        catch(Exception e){
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }
}
