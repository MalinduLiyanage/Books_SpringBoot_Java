package com.malinduliyanage.books.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequest {

    private String bookName;

    private String authorName;

    private String description;

}
