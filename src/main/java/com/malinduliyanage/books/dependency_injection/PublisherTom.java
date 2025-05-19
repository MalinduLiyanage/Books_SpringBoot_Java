package com.malinduliyanage.books.dependency_injection;

public class PublisherTom implements BuyBooks{
    @Override
    public void getPrice(int bookId) {
        System.out.println("Publisher Tom " + bookId);
    }
}
