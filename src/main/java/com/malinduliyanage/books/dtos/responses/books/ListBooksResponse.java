package com.malinduliyanage.books.dtos.responses.books;

import com.malinduliyanage.books.dtos.books.BooksDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListBooksResponse {

    private List<BooksDTO> books;

    public ListBooksResponse(List<BooksDTO> books) {
        this.books = books;
    }


    public ListBooksResponse() {
        // default
    }
}
