package con.jdbcconnectivity.BookstoreManagement.controller;

import con.jdbcconnectivity.BookstoreManagement.model.User;
import con.jdbcconnectivity.BookstoreManagement.service.impl.UserServiceImpl;

import java.util.Scanner;

public class UserController {

    private Scanner sc = new Scanner(System.in);
    private UserServiceImpl service = new UserServiceImpl();

    public void menu() {
        while (true) {
            System.out.println("\n===== USER MANAGEMENT =====");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Delete User");
            System.out.println("4. Back");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

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
        System.out.print("User ID: ");
        int id = sc.nextInt();

        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        System.out.print("Role (ADMIN / STAFF): ");
        String role = sc.next();

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
        System.out.print("Enter User ID to delete: ");
        int id = sc.nextInt();
        service.deleteUser(id);
        System.out.println("User deleted successfully.");
    }
}
