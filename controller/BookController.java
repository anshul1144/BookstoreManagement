package con.jdbcconnectivity.BookstoreManagement.controller;

import con.jdbcconnectivity.BookstoreManagement.model.Book;
import con.jdbcconnectivity.BookstoreManagement.service.impl.BookServiceImpl;

import java.util.Scanner;

public class BookController {

    private Scanner sc = new Scanner(System.in);
    private BookServiceImpl service = new BookServiceImpl();

    public void menu() {
        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Back");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    add();
                    break;

                case 2:
                    view();
                    break;

                case 3:
                    update();
                    break;

                case 4:
                    delete();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void add() {
        System.out.print("Book ID: ");
        String id = sc.next();

        System.out.print("Title: ");
        String title = sc.next();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();

        service.addBook(new Book(id, title, price, stock));
    }

    private void view() {
        for (Book b : service.viewBooks()) {
            System.out.println(
                    b.getBookId() + " | " +
                    b.getTitle() + " | " +
                    b.getPrice() + " | " +
                    b.getStock()
            );
        }
    }

    private void update() {
        System.out.print("Book ID: ");
        String id = sc.next();

        System.out.print("New Price: ");
        double price = sc.nextDouble();

        System.out.print("New Stock: ");
        int stock = sc.nextInt();

        Book b = new Book();
        b.setBookId(id);
        b.setPrice(price);
        b.setStock(stock);

        service.updateBook(b);
    }

    private void delete() {
        System.out.print("Book ID: ");
        service.deleteBook(sc.next());
    }
}
