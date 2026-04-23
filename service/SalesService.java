package service;

public interface SalesService {
    void makeSale(String customerId, String bookId, int quantity);

    default void sellBook(String bookId, int quantity) {
        makeSale("CUST001", bookId, quantity);
    }
}
