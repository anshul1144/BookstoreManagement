package dao;

public interface SalesDAO {
    int createSale(String customerId);
    void addSaleDetails(int saleId, String bookId, int quantity);
}
