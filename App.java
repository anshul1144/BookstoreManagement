
import controller.*;
import config.DBConnection;
import util.InputUtil;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try {
            DBConnection.getConnection();
        } catch (IllegalStateException e) {
            System.out.println("\nDatabase initialization failed.");
            System.out.println(e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Reason: " + e.getCause().getMessage());
            }
            return;
        }

        Scanner sc = new Scanner(System.in);
        LoginController login = new LoginController(sc);

        // AUTHENTICATION
        if (!login.loginMenu()) return;

        BookController book = new BookController(sc);
        CustomerController customer = new CustomerController(sc);
        SalesController sales = new SalesController(sc);
        ReportController report = new ReportController(sc);

        while (true) {
            System.out.println("\n===== BOOK STORE MANAGEMENT =====");
            System.out.println("1. Book Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Sales");
            System.out.println("4. Reports");
            System.out.println("5. Logout");

            int ch = InputUtil.readInt(sc, "Enter choice: ");

            switch (ch) {
                case 1:
                    book.menu();
                    break;
                case 2:
                    customer.menu();
                    break;
                case 3:
                    sales.menu();
                    break;
                case 4:
                    report.menu();
                    break;
                case 5:
                    System.out.println("Logged out successfully.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
