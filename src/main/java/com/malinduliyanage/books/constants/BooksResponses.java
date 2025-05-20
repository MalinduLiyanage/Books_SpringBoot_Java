package com.malinduliyanage.books.constants;

import lombok.Getter;

public enum BooksResponses {

    BOOK_EXIST("The Book is already exists!"),
    BOOK_CREATED("The Book is recorded!");

    @Getter
    private final String value;

    BooksResponses(String value) {
        this.value = value;
    }
}
