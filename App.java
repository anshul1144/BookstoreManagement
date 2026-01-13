package con.jdbcconnectivity.BookstoreManagement;

import con.jdbcconnectivity.BookstoreManagement.controller.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        LoginController login = new LoginController();

        // AUTHENTICATION
        if (!login.loginMenu()) return;

        Scanner sc = new Scanner(System.in);

        BookController book = new BookController();
        CustomerController customer = new CustomerController();
        SalesController sales = new SalesController();
        ReportController report = new ReportController();

        while (true) {
            System.out.println("\n===== BOOK STORE MANAGEMENT =====");
            System.out.println("1. Book Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Sales");
            System.out.println("4. Reports");
            System.out.println("5. Logout");

            int ch = sc.nextInt();

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
