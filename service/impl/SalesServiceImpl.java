package service.impl;

import dao.impl.BookDAOImpl;
import dao.impl.CustomerDAOImpl;
import dao.impl.SalesDAOImpl;
import service.SalesService;

public class SalesServiceImpl implements SalesService {

    private final SalesDAOImpl salesDao = new SalesDAOImpl();
    private final BookDAOImpl bookDao = new BookDAOImpl();
    private final CustomerDAOImpl customerDao = new CustomerDAOImpl();

    @Override
    public void makeSale(String customerId, String bookId, int qty) {
        if (qty <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        if (!customerDao.existsCustomer(customerId)) {
            System.out.println("Customer not found.");
            return;
        }

        if (!bookDao.reduceStockIfAvailable(bookId, qty)) {
            System.out.println("Book not found or insufficient stock.");
            return;
        }

        int saleId = salesDao.createSale(customerId);
        if (saleId > 0) {
            salesDao.addSaleDetails(saleId, bookId, qty);
            System.out.println("Sale completed successfully.");
        } else {
            System.out.println("Sale failed.");
        }
    }
}
