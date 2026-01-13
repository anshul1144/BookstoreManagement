package con.jdbcconnectivity.BookstoreManagement.controller;

import con.jdbcconnectivity.BookstoreManagement.model.User;
import con.jdbcconnectivity.BookstoreManagement.service.impl.UserServiceImpl;

import java.util.Scanner;

public class LoginController {

    private Scanner sc = new Scanner(System.in);
    private UserServiceImpl service = new UserServiceImpl();

    public boolean loginMenu() {
        while (true) {
            System.out.println("\n===== USER AUTHENTICATION =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    register();
                    break;

                case 2:
                    if (login()) return true;
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void register() {
        System.out.print("Username: ");
        String username = sc.next();
        System.out.print("Password: ");
        String password = sc.next();

        boolean success = service.register(new User(username, password));
        if (success)
            System.out.println("Registration successful!");
    }

    private boolean login() {
        System.out.print("Username: ");
        String username = sc.next();
        System.out.print("Password: ");
        String password = sc.next();

        if (service.login(username, password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }
}
