package com.malinduliyanage.books.constants;

import lombok.Getter;

//An enum for returning various response messages
public enum CommonResponse {

    INTERNAL_SERVER_ERROR("Internal Server Error. Please refer to the internal server logs"),
    RECORD_NOT_FOUND("Requested record not found.");

    @Getter
    private final String value;

    CommonResponse(String value) {
        this.value = value;
    }
}

