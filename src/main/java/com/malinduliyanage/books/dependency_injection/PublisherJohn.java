package com.malinduliyanage.books.dependency_injection;

public class PublisherJohn implements BuyBooks{
    @Override
    public void getPrice(int bookId) {
        System.out.println("Publisher John " + bookId);
    }
}
