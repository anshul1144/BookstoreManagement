package controller;
import java.util.Scanner;

import service.impl.SalesServiceImpl;
import util.InputUtil;

public class SalesController {

    private final Scanner sc;
    private final SalesServiceImpl service = new SalesServiceImpl();

    public SalesController(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        String customerId = InputUtil.readText(sc, "Customer ID: ");
        String bookId = InputUtil.readText(sc, "Book ID: ");
        int qty = InputUtil.readInt(sc, "Quantity: ");

        service.makeSale(customerId, bookId, qty);
    }
}
