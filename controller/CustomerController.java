package con.jdbcconnectivity.BookstoreManagement.controller;

import con.jdbcconnectivity.BookstoreManagement.model.Customer;
import con.jdbcconnectivity.BookstoreManagement.service.impl.CustomerServiceImpl;

import java.util.Scanner;

public class CustomerController {

    private Scanner sc = new Scanner(System.in);
    private CustomerServiceImpl service = new CustomerServiceImpl();

    public void menu() {
        while (true) {
            System.out.println("\n1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Back");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    add();
                    break;

                case 2:
                    view();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void add() {
        System.out.print("Customer ID: ");
        String id = sc.next();

        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Phone: ");
        String phone = sc.next();

        service.addCustomer(new Customer(id, name, phone));
    }

    private void view() {
        for (Customer c : service.viewCustomers()) {
            System.out.println(
                    c.getCustomerId() + " | " +
                    c.getName() + " | " +
                    c.getPhone()
            );
        }
    }
}
