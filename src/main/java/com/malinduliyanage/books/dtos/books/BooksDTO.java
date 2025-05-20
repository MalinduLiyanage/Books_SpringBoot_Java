package com.malinduliyanage.books.dtos.books;

import com.malinduliyanage.books.entities.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BooksDTO {

    private int id;
    private String bookName;
    private String authorName;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BooksDTO () {}

    public BooksDTO (BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.bookName = bookEntity.getBookName();
        this.authorName = bookEntity.getAuthorName();
        this.description = bookEntity.getDescription();
        this.createdAt = bookEntity.getCreatedAt();
        this.updatedAt = bookEntity.getUpdatedAt();
    }
}
