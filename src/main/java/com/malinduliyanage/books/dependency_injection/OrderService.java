package com.malinduliyanage.books.dependency_injection;

public class OrderService {

    private final BuyBooks buyBooks;

    public OrderService(BuyBooks buyBooks){
        this.buyBooks = buyBooks;
    }

    public void buyBook(){
        buyBooks.getPrice(10);
    }
}
