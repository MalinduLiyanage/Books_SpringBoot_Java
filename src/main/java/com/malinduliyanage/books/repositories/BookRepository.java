//Repository is a Java interface used to perform DB operations on entities
//Handles CRUD logic

package com.malinduliyanage.books.repositories;

import com.malinduliyanage.books.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    BookEntity findByBookName(String bookName);

    BookEntity findById(int bookId);

}
