package con.jdbcconnectivity.BookstoreManagement.controller;
import java.util.Scanner;

import con.jdbcconnectivity.BookstoreManagement.service.impl.ReportServiceImpl;

public class ReportController {

    private Scanner sc = new Scanner(System.in);
    private ReportServiceImpl service = new ReportServiceImpl();

    public void menu() {
        while (true) {
            System.out.println("\n1. Sales Report");
            System.out.println("2. Stock Report");
            System.out.println("3. Low Stock Report");
            System.out.println("4. Back");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    service.salesReport();
                    break;

                case 2:
                    service.stockReport();
                    break;

                case 3:
                    System.out.print("Enter stock threshold: ");
                    service.lowStockReport(sc.nextInt());
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


