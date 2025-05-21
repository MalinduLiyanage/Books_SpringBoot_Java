package com.malinduliyanage.books.constants;

import lombok.Getter;

public enum UserResponses {
    USER_EXIST("The user is already exists!"),
    USER_NO_EXIST("Invalid Credentials!"),
    USER_CREATED("The user is recorded!"),
    USER_UPDATED("The user is updated!");

    @Getter
    private final String value;

    UserResponses(String value) {
        this.value = value;
    }
}
