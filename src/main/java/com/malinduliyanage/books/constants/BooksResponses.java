package com.malinduliyanage.books.constants;

import lombok.Getter;

public enum BooksResponses {

    BOOK_EXIST("The Book is already exists!"),
    BOOK_NO_EXIST("The Book is not found!"),
    BOOK_CREATED("The Book is recorded!"),
    BOOK_UPDATED("The Book is updated!");

    @Getter
    private final String value;

    BooksResponses(String value) {
        this.value = value;
    }
}
