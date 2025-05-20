//Entity is a Java class that maps to a table in the database
//It Represents the data structure
//Used for defining columns, primary key, timestamps, etc. and maintains data

package com.malinduliyanage.books.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

//We use JPA (Java Persistent API) for
@Setter
@Getter
// @Entity marks this class as a JPA entity (i.e., it maps to a DB table)
@Entity
@Table(name = "book")
public class BookEntity {

    //@Id → Marks this field as the primary key
    //@GeneratedValue(...) → Lets the DB auto-generate the value (e.g., AUTO_INCREMENT in MySQL)
    @Id
    // strategy specifies how the ID should be generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Can state @Column to pointout which column we consider in the selected table
    //If not, it takes the variable name as corresponding to the col name
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_name")
    private String authorName;

    private String description;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
