package com.malinduliyanage.books.dtos.requests.books;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookRequest {

    private String bookName;

    private String authorName;

    private String description;
}
