package controller;

import model.User;
import service.impl.UserServiceImpl;
import util.InputUtil;

import java.util.Scanner;

public class LoginController {

    private final Scanner sc;
    private final UserServiceImpl service = new UserServiceImpl();

    public LoginController(Scanner sc) {
        this.sc = sc;
    }

    public boolean loginMenu() {
        while (true) {
            System.out.println("\n===== USER AUTHENTICATION =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int ch = InputUtil.readInt(sc, "Enter choice: ");

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
        String username = InputUtil.readText(sc, "Username: ");
        String password = InputUtil.readText(sc, "Password: ");

        boolean success = service.register(new User(username, password));
        if (success)
            System.out.println("Registration successful!");
    }

    private boolean login() {
        String username = InputUtil.readText(sc, "Username: ");
        String password = InputUtil.readText(sc, "Password: ");

        if (service.login(username, password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }
}
