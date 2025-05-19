package com.malinduliyanage.books;

import com.malinduliyanage.books.dependency_injection.OrderService;
import com.malinduliyanage.books.dependency_injection.PublisherJohn;
import com.malinduliyanage.books.dependency_injection.PublisherTom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) {
		OrderService orderService = new OrderService(new PublisherJohn());
		orderService.buyBook();
		SpringApplication.run(BooksApplication.class, args);
	}

}
