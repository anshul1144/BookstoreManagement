package con.jdbcconnectivity.BookstoreManagement.service.impl;

import con.jdbcconnectivity.BookstoreManagement.dao.impl.BookDAOImpl;
import con.jdbcconnectivity.BookstoreManagement.dao.impl.SalesDAOImpl;

public class SalesServiceImpl {

    private SalesDAOImpl salesDao = new SalesDAOImpl();
    private BookDAOImpl bookDao = new BookDAOImpl();

    public void makeSale(String customerId, String bookId, int qty) {
        int saleId = salesDao.createSale(customerId);
        if (saleId > 0) {
            salesDao.addSaleDetails(saleId, bookId, qty);
            bookDao.updateStock(bookId, qty);
            System.out.println("Sale completed successfully!");
        }
    }
}
