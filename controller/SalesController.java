package con.jdbcconnectivity.BookstoreManagement.controller;
import java.util.Scanner;

import con.jdbcconnectivity.BookstoreManagement.service.impl.SalesServiceImpl;

public class SalesController {

    private Scanner sc = new Scanner(System.in);
    private SalesServiceImpl service = new SalesServiceImpl();

    public void menu() {
        System.out.print("Book ID: ");
        String bookId = sc.next();
        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        service.sellBook(bookId, qty);
    }
}
