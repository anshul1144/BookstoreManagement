package controller;

import model.Customer;
import service.impl.CustomerServiceImpl;
import util.InputUtil;

import java.util.Scanner;

public class CustomerController {

    private final Scanner sc;
    private final CustomerServiceImpl service = new CustomerServiceImpl();

    public CustomerController(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Back");

            int ch = InputUtil.readInt(sc, "Enter choice: ");

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
        String id = InputUtil.readText(sc, "Customer ID: ");
        String name = InputUtil.readText(sc, "Name: ");
        String phone = InputUtil.readText(sc, "Phone: ");

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
