package con.jdbcconnectivity.BookstoreManagement.dao;


public interface SalesDAO {
    void makeSale(String bookId, int quantity);
}

