package controller;
import java.util.Scanner;

import service.impl.ReportServiceImpl;
import util.InputUtil;

public class ReportController {

    private final Scanner sc;
    private final ReportServiceImpl service = new ReportServiceImpl();

    public ReportController(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Sales Report");
            System.out.println("2. Stock Report");
            System.out.println("3. Low Stock Report");
            System.out.println("4. Back");

            int ch = InputUtil.readInt(sc, "Enter choice: ");

            switch (ch) {
                case 1:
                    service.salesReport();
                    break;

                case 2:
                    service.stockReport();
                    break;

                case 3:
                    service.lowStockReport(InputUtil.readInt(sc, "Enter stock threshold: "));
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

