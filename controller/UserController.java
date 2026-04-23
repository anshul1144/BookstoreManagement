package controller;

import model.User;
import service.impl.UserServiceImpl;
import util.InputUtil;

import java.util.Scanner;

public class UserController {

    private final Scanner sc;
    private final UserServiceImpl service = new UserServiceImpl();

    public UserController(Scanner sc) {
        this.sc = sc;
    }

    public void menu() {
        while (true) {
            System.out.println("\n===== USER MANAGEMENT =====");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Delete User");
            System.out.println("4. Back");

            int ch = InputUtil.readInt(sc, "Enter choice: ");

            switch (ch) {
                case 1:
                    addUser();
                    break;

                case 2:
                    viewUsers();
                    break;

                case 3:
                    deleteUser();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addUser() {
        int id = InputUtil.readInt(sc, "User ID: ");
        String username = InputUtil.readText(sc, "Username: ");
        String password = InputUtil.readText(sc, "Password: ");
        String role = InputUtil.readText(sc, "Role (ADMIN / STAFF): ");

        User user = new User(id, username, password, role);
        service.addUser(user);

        System.out.println("User added successfully.");
    }

    private void viewUsers() {
        System.out.println("\n--- USER LIST ---");
        for (User u : service.viewUsers()) {
            System.out.println(
                    u.getUserId() + " | " +
                    u.getUsername() + " | " +
                    u.getRole()
            );
        }
    }

    private void deleteUser() {
        int id = InputUtil.readInt(sc, "Enter User ID to delete: ");
        service.deleteUser(id);
        System.out.println("User deleted successfully.");
    }
}
